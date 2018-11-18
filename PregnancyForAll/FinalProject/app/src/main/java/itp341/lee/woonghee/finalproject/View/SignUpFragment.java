package itp341.lee.woonghee.finalproject.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import itp341.lee.woonghee.finalproject.Model.UserDbHelper;
import itp341.lee.woonghee.finalproject.R;

public class SignUpFragment extends Fragment {

    private EditText first, last, mEmail, mPassword;
    private Button create;
    private TextView terms;

    private ImageView profile_picture;
    private ImageButton select_pic_button;
    private static final int SELECTED_PICTURE = 1;
    private Uri selected_image_uri;

    //database to add user
    private Context context;

    public SignUpFragment() {} // Required empty public constructor

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        first = (EditText) v.findViewById(R.id.first_name_textbox);
        last = (EditText) v.findViewById(R.id.last_name_textbox);
        mEmail = (EditText) v.findViewById(R.id.email_textbox);
        mPassword = (EditText) v.findViewById(R.id.password_textbox);
        terms = (TextView) v.findViewById(R.id.terms_of_service);
        create = (Button) v.findViewById(R.id.create_account);

        profile_picture = (ImageView) v.findViewById(R.id.profile_image);
        select_pic_button = (ImageButton) v.findViewById(R.id.select_pic_button);

        select_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECTED_PICTURE);
            }
        });

        this.context = getActivity().getApplicationContext(); // get the context
        selected_image_uri = null;

        //when button is clicked, save the data to the storage
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if at least one of the fields is empty, no action
                if(first.getText().toString().isEmpty()||
                    last.getText().toString().isEmpty()||
                    mEmail.getText().toString().isEmpty()||
                    mPassword.getText().toString().isEmpty()){
                    return;
                }
                //if email already exists, toast and return. otherwise, add user to database
                //true = succeed to add user, false = failed to add user
                if(!addUser(view)){
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.email_exist_toast),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //launch activity after storing the user to database
                Intent i = new Intent(getActivity().getApplicationContext(), UserActivity.class);
                if(selected_image_uri==null){
                    i.putExtra("uri","null");
                }
                else{
                    i.putExtra("uri",selected_image_uri.toString());
                }
                startActivity(i);
                getActivity().finish();
            }
        });
        //when this is clicked open the term activity
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), TermsActivity.class);
                startActivity(i);
            }
        });
        return v;
    }
    //add user to database
    public boolean addUser(View view){

        boolean exists = UserDbHelper.getInstance(context).userExists(mEmail.getText().toString());
        if(exists){return false; } //failed to add user because it alreadys exists
        //gather all info
        String first_name = first.getText().toString();
        String last_name = last.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        UserDbHelper.getInstance(context).add_user_to_database(first_name,last_name,email,password);
        return true; //succeed to add user
    }
    //coming back from camera gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case SELECTED_PICTURE:
                if(resultCode==getActivity().RESULT_OK){
                    selected_image_uri = data.getData();
                    profile_picture.setImageURI(selected_image_uri);
                }
        }
    }
}