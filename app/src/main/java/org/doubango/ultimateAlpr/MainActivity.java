package org.doubango.ultimateAlpr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText eRegistration_No,eOwner_Name,eRegistration_Date,eChassis_No,eVehicle_Class,eModel ,eEngine_No,eFuel, eRC_Status;
    FirebaseDatabase database;
    DatabaseReference ref;
    NumberPlate user;
    String internetPermission[];
    private static final int INTERNET_REQUEST_CODE =200;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eRegistration_No=findViewById(R.id.Registration_No);
        eOwner_Name=findViewById(R.id.Owner_Name);
        eRegistration_Date=findViewById(R.id.Registration_Date);
        eChassis_No=findViewById(R.id.Chassis_No);
        eVehicle_Class=findViewById(R.id.Vehicle_Class);
        eModel=findViewById(R.id.Model);
        eEngine_No=findViewById(R.id.Engine_No);
        eFuel=findViewById(R.id.Fuel);
        eRC_Status=findViewById(R.id.RC_Status);
        database=FirebaseDatabase.getInstance();
user=new NumberPlate();
        ref=database.getReference("Registered_Vehicles");


    }

    public void register(View view) {


       ref.child(eRegistration_No.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    eRegistration_No.setError( "Number Plate is already exists..");
                } else {

                    ref.addValueEventListener(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            getValue();
                           ref.child(eRegistration_No.getText().toString()).setValue(user);
                            Toast.makeText(MainActivity.this, "Registration successfully...", Toast.LENGTH_LONG).show();



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    private  void getValue(){

        user.setRegistration_No(eRegistration_No.getText().toString());
        user.setOwner_Name(eOwner_Name.getText().toString());
        user.setRegistration_Date(eRegistration_Date.getText().toString());
        user.setChassis_No(eChassis_No.getText().toString());
        user.setVehicle_Class(eVehicle_Class.getText().toString());
        user.setModel(eModel.getText().toString());
        user.setEngine_No(eEngine_No.getText().toString());
        user.setFuel(eFuel.getText().toString());
        user.setRC_Status(eRC_Status.getText().toString());



    }
}
