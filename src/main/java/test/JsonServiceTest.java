package test;

import javax.ws.rs.core.MultivaluedMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class JsonServiceTest {

	@Test
	public void JsonTest() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client
				.resource("http://acc.icpc.org.ua/user/baylor");
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();		
		formData.add("email", "111@mailinator.com");
		formData.add("password", "123456");		
		ClientResponse response = webResource.queryParams(formData)
				.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.header("X-Requested-With", "XMLHttpRequest")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0")
				.header("Accept", "*/*")
				.header("Accept-Encoding","gzip,deflate,sdch")
				.header("Cookie", "PHPSESSID=eovcs964t7qv6ndrepneo1cdu6; language=en; _ga=GA1.3.1672955171.1407998251")						
				.post(ClientResponse.class);		
		System.out.println(response.getStatus());
		Assert.assertEquals(response.getStatus(), 200); 
		

	}

}
