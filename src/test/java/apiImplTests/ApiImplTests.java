package apiImplTests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import controller.API;
import controller.ApiImpl;

/**
 * 
 * @author westsere
 *
 */

@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ApiImplTests {

	private final static String RATINGS_FILE = "src/test/resources/ratings_Video_Games.csv";
	private static API api = new ApiImpl();

	/**
	 * Processes data from a file & splits into strings, 
	 * and sends each string as an argument to the API add all method.
	 * 
	 * @return the number of lines of data processed.
	 */
	public static int read() {

		int ret = -1;

		try {		
			BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream( RATINGS_FILE ) ) );

			String strLine = null;

			while( ( strLine = br.readLine() ) != null ) {

				String[] dataArray = strLine.split(",");

				api.addAll( dataArray[0], dataArray[1], dataArray[2], dataArray[3]);

				++ret;;
			} 
			br.close();
		}
		catch( Exception ex ){ System.err.println( ex.getMessage() ); }

		return ret;		
	}
	
	/**
	 * Tests for null productId input
	 */
	@Test 
	public void a1TestNullGetConsumersOfProduct() {

		String productId = null;

		assertNull(api.getConsumersOfProduct( productId ) );
	}
	
	/**
	 * Tests for null consumerId input
	 */
	@Test 
	public void a2TestNullGetRatedProducts() {

		String consumerId = null;

		assertNull( api.getRatedProducts( consumerId ) );
	}

	/**
	 * Tests for null consumerId input
	 */
	@Test 
	public void a3TestNullGetConsumerAvgRating() {

		String consumerId = null;

		assertNull( api.getConsumerAvgRating( consumerId ) );
	}
	
	/**
	 * Tests that the customers who rated a given product are correct
	 */
	@Test 
	public void a4TestGetConsumersOfProduct() {
		
		read();
		
		String productId = "B00LGBJIQ4";
		
		List<String> expectedResult = Arrays.asList( "AEEMJX418B5RC","A265KF0CQ058RZ",
												"A20J2PMC9ZPD4F","A3HMVWAGUCNA1K" );

		assertArrayEquals ( api.getConsumersOfProduct( productId ).toArray(), expectedResult.toArray() );
	}
	
	/**
	 * Tests that the products rated by a given customer are correct
	 */
	@Test 
	public void a5TestGetRatedProducts() { 
		
		String consumerId = "A3LEQOLIXQU7KS";

		List<String> expectedResult = Arrays.asList( "B009VJJIBI", "B00HK3RDPK", "B00HNFLDL0",
													 "B00HNGGYPE", "B00I9AZ7L0", "B00IK5URPU",
													 "B00IUHNLZG", "B00J4SYXPC", "B00JAQFVJK",
													 "B00JAQFXCU", "B00JRMPL8I", "B00KB2PGT2",
													 "B00KGBQD5E", "B00KQYCHJM", "B00KRHWVTY",
													 "B00L45HS50", "B00LA7IBOY", "B00LA8EFAW", "B00LIWF1GW");

		assertArrayEquals( api.getRatedProducts( consumerId ).toArray(), expectedResult.toArray() );
	}
	
	/**
	 * Tests that a consumer's average rating is correct
	 */
	@Test 
	public void a6TestgetConsumerAvgRating() { 

		String consumerId = "A3LEQOLIXQU7KS";
		
		Double expectedResult = 3.4210526315789473;

		assertEquals( api.getConsumerAvgRating( consumerId ), expectedResult , 0.001);
	}
	
	
	// Working hard-coded test for returning consumers of a product.

/*	@Test // tests that the customers who rated a given product are correct
	public void a4TestGetConsumersOfProduct() {

		String stringData1 = "AWWQVZ7EYH00Y,3866811659,4.7,1394323200"; 
		String stringData2 = "A1XS2BTMVNE7IN,3866811659,3.0,1324425600"; 

		String[] dataArray1 = stringData1.split(",");
		String[] dataArray2 = stringData2.split(",");

		api.addConsumer(dataArray1[0], dataArray1[1]);
		api.addConsumer(dataArray2[0], dataArray2[1]);

		List<String> expectedResult = Arrays.asList( "AWWQVZ7EYH00Y", "A1XS2BTMVNE7IN");

		assertArrayEquals ( api.getConsumersOfProduct("3866811659").toArray(), expectedResult.toArray() );
	} */
	
	
	// Working hard-coded test for returning products a consumer has rated.
	
/*	@Test // tests that the products rated by a given customer are correct
	public void a5TestGetRatedProducts() { 

		String stringData1 = "AWWQVZ7EYH00Y,3866811659,4.7,1394323200"; 
		String stringData2 = "AWWQVZ7EYH00Y,5293009893,2.4,1394323200"; 

		String[] dataArray1 = stringData1.split(",");
		String[] dataArray2 = stringData2.split(",");

		api.addProduct(dataArray1[0], dataArray1[1]);
		api.addProduct(dataArray2[0], dataArray2[1]);

		List<String> expectedResult = Arrays.asList( "3866811659", "5293009893");

		assertArrayEquals( api.getRatedProducts("AWWQVZ7EYH00Y").toArray(), expectedResult.toArray() );
	}*/
	
	
	// Working hard-coded test for returning consumer average rating.

/*	@Test // tests that a consumer's average rating is correct
	public void a6TestgetConsumerAvgRating() { 

		String stringData1 = "AWWQVZ7EYH00Y,3866811659,4.7,1394323200"; 
		String stringData2 = "AWWQVZ7EYH00Y,5293009893,2.4,1394323200"; 

		String[] dataArray1 = stringData1.split(",");
		String[] dataArray2 = stringData2.split(",");

		api.addRating(dataArray1[0], dataArray1[2], dataArray1[3]);
		api.addRating(dataArray2[0], dataArray2[2], dataArray2[3]);

		Double expectedResult = 3.55;

		assertEquals( api.getConsumerAvgRating( "AWWQVZ7EYH00Y" ), expectedResult , 0.001);
	}*/

}