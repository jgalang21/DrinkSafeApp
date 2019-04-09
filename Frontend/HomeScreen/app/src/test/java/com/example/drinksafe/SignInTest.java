package com.example.drinksafe;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class SignInTest {
    @Mock
    SignIn mockSignin;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    //should take you to the signUp page
/*    @Test
    public void signInTestSignUp(){
        //mock(SignIn.)
    }*/
    @Test
    public void signInTestSuccess() throws JSONException {
        SignIn test = mock(SignIn.class);
        SignIn testLoginSuccess = new SignIn();
        String userCorrect = "BigHAAS";
        String passCorrect = "password123";

        JSONObject response = new JSONObject();
        response.put("loginSuccess", new Boolean(true));
        when(test.getResponse(userCorrect,passCorrect)).thenReturn(response);
        System.out.println(response.getBoolean("loginSuccess"));
        Assert.assertEquals(testLoginSuccess.tryLogin(userCorrect,passCorrect,test),response.getBoolean("loginSuccess"));
    }
    @Test
    public void signInTestFailedUser()throws JSONException{
        SignIn test = mock(SignIn.class);
        SignIn testLoginFail = new SignIn();
        String userInCorrect = "BigHAASisaPoo";
        String passInCorrect = "password123";

        JSONObject response = new JSONObject();
        response.put("loginSuccess2", new Boolean(false));
        when(test.getResponse(userInCorrect,passInCorrect)).thenReturn(response);
        System.out.println(response.getBoolean("loginSuccess2"));
        Assert.assertEquals(testLoginFail.tryLogin(userInCorrect,passInCorrect,test),response.getBoolean("loginSuccess2"));
    }
    @Test
    public void signInTestFailedPass()throws JSONException{
        SignIn test = mock(SignIn.class);
        SignIn testLoginFail = new SignIn();
        String userInCorrect = "BigHAAS";
        String passInCorrect = "password123";//4IdeclareThumbwar

        JSONObject response = new JSONObject();
        response.put("loginSuccess3", new Boolean(false));
        when(test.getResponse(userInCorrect,passInCorrect)).thenReturn(response);
        System.out.println(response.getBoolean("loginSuccess3"));
        Assert.assertEquals(testLoginFail.tryLogin(userInCorrect,passInCorrect,test),response.getBoolean("loginSuccess3"));
    }
}
