package controller;

import java.util.List;

/**
 * 
 * @author westsere
 *
 */

public interface API {
	
	public void addAll( String consumerId, String productId, String rating, String timestamp );
	
	public void addConsumer( String consumerId, String productId );
	
	public void addProduct( String consumerId, String productId );
	
	public void addRating( String consumerId, String rating, String timestamp );
	
	public List<String> getConsumersOfProduct( String productId );
	
	public List<String> getRatedProducts( String consumerId );
	
	public Double getConsumerAvgRating ( String consumerId );
}
