package test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;
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
	public void ImportTest() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client
				.resource("http://acc.icpc.org.ua/user/baylor");
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("email", "111@mailinator.com");
		formData.add("password", "123456");
		ClientResponse response = webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.header("X-Requested-With", "XMLHttpRequest")
				.header("Accept-Language", "en-US,en;q=0.8")
				.post(ClientResponse.class, formData);

		JSONObject obj = new JSONObject(response.getEntity(String.class));

		Assert.assertEquals(obj.get("errors"), false);
		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void registrationTest() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(
				"http://acc.icpc.org.ua/auth/signup").build());

		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("firstNameUk", "test");
		formData.add("middleNameUk", "test");
		formData.add("lastNameUk", "test");
		
		formData.add("email", "Den" + (int) (Math.random() * 10000 + 1)
				+ "@mail.ru");
		formData.add("password", "123456");
		formData.add("passwordRepeat", "123456");
		formData.add("schoolId", "526e889144579850658b456f");
		formData.add("type", "student");
		formData.add("rulesAgree", "1");
		formData.add("recaptchaIgnore", "1");

		ClientResponse response = webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.header("X-Requested-With", "XMLHttpRequest")
				.header("Accept-Language", "en-US,en;q=0.8")
				.post(ClientResponse.class, formData);
		System.out.println(response.getStatus());
			
		JSONObject obj = new JSONObject(response.getEntity(String.class));
		Assert.assertEquals(obj.get("errors"), false);
		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void registrationNegativeTest() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(
				"http://acc.icpc.org.ua/auth/signup").build());
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("firstNameUk", "test");
		formData.add("middleNameUk", "test");
		formData.add("lastNameUk", "test");
		formData.add("email", "111@mailinator.com");
		formData.add("password", "123456");
		formData.add("passwordRepeat", "123456");
		formData.add("schoolId", "526e889144579850658b456f");
		formData.add("type", "student");
		formData.add("rulesAgree", "1");
		formData.add("recaptchaIgnore", "1");

		ClientResponse response = webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.header("X-Requested-With", "XMLHttpRequest")
				.header("Accept-Language", "en-US,en;q=0.8")
				.post(ClientResponse.class, formData);
		System.out.println(response.getStatus());		

		JSONObject obj = new JSONObject(response.getEntity(String.class));

		Assert.assertTrue((obj.get("errors")).toString().contains("Email is not unique in DB."));

		Assert.assertEquals(response.getStatus(), 200);
	}

	
	@Test
	public void simple(){
			
	}
}
