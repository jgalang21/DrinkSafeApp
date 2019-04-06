package com.example.drinksafe;
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
    public void signInTest2(){
        SignIn test = mock(SignIn.class);
        String userCorrect = "jorden@hotmail.com";
        String passCorrect = "drinksafe";
    }
    @Test
    public void signInTest3(){

    }
}
