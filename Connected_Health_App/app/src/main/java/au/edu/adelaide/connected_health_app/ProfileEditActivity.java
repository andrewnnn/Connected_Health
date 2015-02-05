package au.edu.adelaide.connected_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ProfileEditActivity extends QuickMenu {
    private EditText editFirstNameInput;
    private EditText editLastNameInput;
    private EditText editAddressInput;
    private EditText editPhoneInput;
    private EditText editEmailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        editFirstNameInput = (EditText)findViewById(R.id.edit_first_name);
        editLastNameInput = (EditText)findViewById(R.id.edit_last_name);
        editAddressInput = (EditText)findViewById(R.id.edit_address);
        editPhoneInput = (EditText)findViewById(R.id.edit_phone);
        editEmailInput = (EditText)findViewById(R.id.edit_email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Edit profile values from input */
    public void editProfile(View view) {
        String newFirstName = editFirstNameInput.getText().toString();
        String newLastName = editLastNameInput.getText().toString();
        String newAddress = editAddressInput.getText().toString();
        String newPhone = editPhoneInput.getText().toString();
        String newEmail = editEmailInput.getText().toString();

        Intent intent = new Intent(this, ProfileViewActivity.class);
        intent.putExtra("newFirstName", newFirstName);
        intent.putExtra("newLastName", newLastName);
        intent.putExtra("newAddress", newAddress);
        intent.putExtra("newPhone", newPhone);
        intent.putExtra("newEmail", newEmail);
        startActivity(intent);
    }
}
