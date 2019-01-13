package controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author westsere
 *
 */

public class ApiImpl implements API {
	
	private Map<String, List<String>> consumersRatedProduct; // Key = product, value = consumers
	private Map<String, List<String>> productsRatedByConsumers;// Key = consumer, value = products
	private Map<String, List<Double>> consumerRatingValues;// Key = consumer, value = ratings
	private Map<String, List<String>> consumerRatingTimestamps;// Key consumer, value = timestamps
	
	/**
	 * Calls all other "add" methods to add all of the rating information simultaneously.
	 */
	public void addAll( String consumerId, String productId, String rating, String timestamp ) {
	
		addConsumer( consumerId, productId );
		
		addProduct( consumerId, productId );
		
		addRating( consumerId, rating, timestamp );	
	}
	
	/**
	 * Adds key and values to the Hashtable storing the consumers who rated a specified product.
	 */
	public void addConsumer( String consumerId, String productId ) {
		
		if( consumersRatedProduct == null ) consumersRatedProduct = new Hashtable<String, List<String>>();
		
		List<String> consumersOfProduct = consumersRatedProduct.get( productId );
		
		if( consumersOfProduct == null ) consumersOfProduct = new ArrayList<String>();
		
		consumersOfProduct.add( consumerId );
		
		consumersRatedProduct.put( productId, consumersOfProduct );	
	}
	
	/**
	 * Adds key and values to the Hashtable storing the products a specified consumer has rated.
	 */
	public void addProduct( String consumerId, String productId ) {
		
		if( productsRatedByConsumers == null ) productsRatedByConsumers = new Hashtable<String, List<String>>();
		
		List<String> ratedProducts = productsRatedByConsumers.get( consumerId );
		
		if( ratedProducts == null ) ratedProducts = new ArrayList<String>();
		
		ratedProducts.add( productId );
		
		productsRatedByConsumers.put( consumerId, ratedProducts );
	}
	
	/**
	 * Adds key and values to the Hashtable storing the ratings a specified consumer has given.
	 */
	public void addRating( String consumerId, String rating, String timestamp ) {
		
		if( consumerRatingValues == null ) consumerRatingValues = new Hashtable<String, List<Double>>();
		
		List<Double> consumerRatings = consumerRatingValues.get( consumerId );
		
		Double doubleRating = Double.parseDouble( rating );
		
		if ( consumerRatings == null ) consumerRatings = new ArrayList<Double>();
		
		consumerRatings.add( doubleRating );
		
		consumerRatingValues.put( consumerId, consumerRatings );
		
		addRatingTimestamp( consumerId, timestamp );
	}
	
	/**
	 * Adds key and values to the Hashtable storing the timestamps of the rating a specified consumer has given.
	 * Private method, only accessed through the "addRating" method.
	 */
	private void addRatingTimestamp( String consumerId, String timestamp ) { 
		
		if( consumerRatingTimestamps == null ) consumerRatingTimestamps = new Hashtable<String, List<String>>();
		
		List<String> ratingTimestamps = consumerRatingTimestamps.get( consumerId );
		
		if ( ratingTimestamps == null ) ratingTimestamps = new ArrayList<String>();
		
		ratingTimestamps.add( timestamp );
		
		consumerRatingTimestamps.put( consumerId, ratingTimestamps );	
	}
	
	/**
	 * Takes a productId and returns the consumers who have rated it.
	 */
	public List<String> getConsumersOfProduct( String productId ){ // returns list of consumers that have rated a specified product
		
		if( productId == null ) return null;
		
		List<String> consumers = consumersRatedProduct.get( productId );
				
		return consumers;
	}
	
	/**
	 * Takes a consumerId and returns the products they have rated.
	 */
	public List<String> getRatedProducts( String consumerId ){ 
		
		if( consumerId == null ) return null;
		
		List<String> products = productsRatedByConsumers.get( consumerId );
		
		return products;
	}
	
	/**
	 * Takes a consumerId and returns the average value of all their product ratings.
	 */
	public Double getConsumerAvgRating ( String consumerId ) { 
		
		if( consumerId == null ) return null;

		List<Double> ratings = consumerRatingValues.get( consumerId );
		
		Double total = 0.0;
		
		for( int i = 0; i < ratings.size(); i++ ) {
			
			total += ratings.get( i );
		}
		Double average = total/ratings.size();
		
		return average;
	}
	
}
