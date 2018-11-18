package itp341.lee.woonghee.finalproject.View;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import itp341.lee.woonghee.finalproject.Model.UserDbHelper;
import itp341.lee.woonghee.finalproject.R;

public class LoginFragment extends Fragment {

    private EditText email, password;
    private TextView signup;
    private Button login;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private Context context;

    //private LoginButton facebook_login_button;
    private CallbackManager mCallbackManager;
    //when you are back from facebook
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        //when you successfully comeback from facebook log in
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile(); //get profile of the person who logged in
            //launch user activity from facebook login
            Intent i = new Intent(getActivity().getApplicationContext(), UserActivity.class);
            i.putExtra("uri","null");
            startActivity(i);
            email.setText(null); password.setText(null);
            getActivity().finish();
        }
        @Override
        public void onCancel() {}
        @Override
        public void onError(FacebookException error) {}
    };
    public LoginFragment() {}// Required empty public constructor
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //facebook
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        AppEventsLogger.activateApp(getActivity().getApplication());
        mCallbackManager = CallbackManager.Factory.create();

        if (getArguments() != null) {}
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        //facebook - Initialize the SDK before executing any other operations,
        LoginButton loginButton = (LoginButton) v.findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker  = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        //--------------------------------------------------

        email = (EditText) v.findViewById(R.id.email_textbox);
        password = (EditText) v.findViewById(R.id.password_textbox);
        signup = (TextView) v.findViewById(R.id.sign_up);
        login = (Button) v.findViewById(R.id.login_button);
        context = getActivity().getApplicationContext();
        //when login button is clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if valid
                boolean valid = validUser(email.getText().toString(), password.getText().toString());
                //if invalid, return

                //for now
                valid = true;
                if(!valid){
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.wrong_combination),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                email.setText(null); password.setText(null);
                //open the main page
                Intent i = new Intent(getActivity().getApplicationContext(), UserActivity.class);
                i.putExtra("uri","null");
                email.setText(null); password.setText(null);
                startActivity(i);
                getActivity().finish();
            }
        });
        //when this clicked, open up sign up activity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), SignUpActivity.class);
                email.setText(null); password.setText(null);
                startActivity(i);
            }
        });
        return v;
    }
    //check if user is valid
    public boolean validUser(String email, String password){
        return UserDbHelper.getInstance(context).validUser(email, password);
    }
    //facebook
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    //resume for facebook
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();

    }
    @Override
    public void onStop(){
        super.onStop();
        accessTokenTracker.stopTracking();;
        profileTracker.stopTracking();
    }

}