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
    @Test
    public void signInTestSignUp(){
        //mock(SignIn.)
    }
    @Test
    public void signInTestSuccess() throws JSONException {
        SignIn test = mock(SignIn.class);
        SignIn testLoginSuccess = new SignIn();
        String userCorrect = "BigHAAS";
        String passCorrect = "password123";

        JSONObject response = new JSONObject();
        response.put("loginSuccess", new Boolean(true));
        when(test.getResponse(userCorrect,passCorrect)).thenReturn(response);
        Assert.assertEquals(testLoginSuccess.tryLogin(userCorrect,passCorrect,test),response.getBoolean("loginSuccess"));
    }
    @Test
    public void signInTestFailedUser()throws JSONException{
        SignIn test = mock(SignIn.class);
        SignIn testLoginFail = new SignIn();
        String userInCorrect = "jordan@hotmail.com";
        String passInCorrect = "drinksafe";

        JSONObject response = new JSONObject();
        response.put("loginSuccess", new Boolean(false));
        when(test.getResponse(userInCorrect,passInCorrect)).thenReturn(response);
        Assert.assertEquals(testLoginFail.tryLogin(userInCorrect,passInCorrect,test),response.getBoolean("loginSuccess"));
    }
    @Test
    public void signInTestFailedPass()throws JSONException{
        SignIn test = mock(SignIn.class);
        SignIn testLoginFail = new SignIn();
        String userInCorrect = "jorden@hotmail.com";
        String passInCorrect = "DRANKSAFE";

        JSONObject response = new JSONObject();
        response.put("loginSuccess", new Boolean(false));
        when(test.getResponse(userInCorrect,passInCorrect)).thenReturn(response);
        Assert.assertEquals(testLoginFail.tryLogin(userInCorrect,passInCorrect,test),response.getBoolean("loginSuccess"));
    }
}
