package com.example.spm1.UI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.spm1.Entities.Parcel;
import com.example.spm1.R;
import com.example.spm1.Utils.AddParcelState;
import com.example.spm1.Utils.ParcelStatus;
import com.example.spm1.Utils.ParcelType;
import com.example.spm1.Utils.ParcelWeight;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class AddParcelActivity extends AppCompatActivity implements View.OnClickListener {

    private ParcelViewModel parcelViewModel;
    Button btnDatePicker, btnSentDatePicker, btnTimePicker, btnSentTimePicker, buttonParcelType, buttonParcelWeight, buttonParcelFragile, buttonDistributionLocation, buttonStatus, buttonAddParcel;
    EditText txtDate, txtSentDate, txtTime, txtSentTime, txtId, txtFName, txtLName, txtAddress, txtPhoneNumber, txtEmail, txtDeliveryManName;
    TextView txtParcelType, txtParcelWeight, txtFragile, txtDistributionLocation, txtStatus;
    private int mYear, mMonth, mDay, mHour, mMinute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcel);
        btnDatePicker = (Button) findViewById(R.id.buttonDate);
        btnTimePicker = (Button) findViewById(R.id.buttonTime);
        btnSentDatePicker = (Button) findViewById(R.id.buttonSentDate);
        btnSentTimePicker = (Button) findViewById(R.id.buttonSentTime);
        buttonParcelType = (Button) findViewById(R.id.parcelTypeButton);
        buttonParcelWeight = (Button) findViewById(R.id.parcelWeightButton);
        buttonParcelFragile = (Button) findViewById(R.id.buttonFragile);
        buttonDistributionLocation = (Button) findViewById(R.id.distributionLocationButton);
        buttonStatus = (Button) findViewById(R.id.statusButton);
        buttonAddParcel = (Button) findViewById(R.id.addParcelButton);


        txtDate = (EditText) findViewById(R.id.inDate);
        txtTime = (EditText) findViewById(R.id.inTime);
        txtSentDate = (EditText) findViewById(R.id.sentDate);
        txtSentTime = (EditText) findViewById(R.id.sentTime);
        txtParcelType = (TextView) findViewById(R.id.parcelTypeTextView);
        txtParcelWeight = (TextView) findViewById(R.id.parcelWeightTextView);
        txtFragile = (TextView) findViewById(R.id.textViewFragile);
        txtDistributionLocation = (TextView) findViewById(R.id.distributionLocationTextView);
        txtStatus = (TextView) findViewById(R.id.statusTextView);
        txtId = (EditText) findViewById(R.id.parcelId);
        txtFName = (EditText) findViewById(R.id.FirstName);
        txtLName = (EditText) findViewById(R.id.LastName);
        txtAddress = (EditText) findViewById(R.id.addressEditText);
        txtPhoneNumber = (EditText) findViewById(R.id.phoneNumberET);
        txtEmail = (EditText) findViewById(R.id.emailET);
        txtDeliveryManName = (EditText) findViewById(R.id.deliveryManNameET);


        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnSentDatePicker.setOnClickListener(this);
        btnSentTimePicker.setOnClickListener(this);
        buttonParcelType.setOnClickListener(this);
        buttonParcelWeight.setOnClickListener(this);
        buttonParcelFragile.setOnClickListener(this);
        buttonDistributionLocation.setOnClickListener(this);
        buttonStatus.setOnClickListener(this);
        buttonAddParcel.setOnClickListener(this);


        parcelViewModel = ViewModelProviders.of(this).get(ParcelViewModel.class);
        parcelViewModel.getParcelChange().observe(this, new Observer<AddParcelState>() {
            @Override
            public void onChanged(AddParcelState state) {
                //Boolean flag = false;
                //Toast toast = Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_SHORT);
                if (state.isSuccess() == true) {
                    //flag = true;
                    //toast =
                    Toast.makeText(getApplicationContext(), "Parcel was added successfully", Toast.LENGTH_SHORT).show();
                    buttonAddParcel.setEnabled(false);
                    //buttonAddParcel.setBackgroundColor(Color.parseColor("@colorPrimary"));

                } else if (state.isFailure() == true) {
                    //flag = true;
                    //toast =
                    Toast.makeText(getApplicationContext(), "Oops something went wrong,parcel was not added", Toast.LENGTH_SHORT).show();
                    buttonAddParcel.setEnabled(true);
                    //buttonAddParcel.setBackgroundColor(Color.parseColor("@colorPrimary"));

                } else {
                    //toast.setMargin(50,50);
                    //toast.show();
                    Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    @Override
    public void onClick (View v){

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnSentDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtSentDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnSentTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtSentTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }


        if (v == buttonParcelType) {
            String parcelType[] = {"envelope", "small package", "big package"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Parcel Type");
            builder.setItems(parcelType, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int position) {
                    switch (position) {
                        case 0:
                            txtParcelType.setText("envelope");
                            break;
                        case 1:
                            txtParcelType.setText("small package");
                            break;
                        case 2:
                            txtParcelType.setText("big package");
                            break;
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }


        if (v == buttonParcelWeight) {
            String parcelWeight[] = {"up to 500 g", "up to 1 kg", "up to 5 kg", "up to 20 kg"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Parcel Weight");
            builder.setItems(parcelWeight, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int position) {
                    switch (position) {
                        case 0:
                            txtParcelWeight.setText("up to 500 g");
                            break;
                        case 1:
                            txtParcelWeight.setText("up to 1 kg");
                            break;
                        case 2:
                            txtParcelWeight.setText("up to 5 kg");
                            break;
                        case 3:
                            txtParcelWeight.setText("up to 20 kg");
                            break;
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        if (v == buttonParcelFragile) {
            String parcelFragile[] = {"YES", "NO"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Is parcel fragile");
            builder.setItems(parcelFragile, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int position) {
                    switch (position) {
                        case 0:
                            txtFragile.setText("YES");
                            break;
                        case 1:
                            txtFragile.setText("NO");
                            break;

                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        if (v == buttonDistributionLocation) {
            String locations[] = {"בית הדפוס 9 ירושלים", "אהרון בראנד 14 ירושלים", "שלמה המלך 4 טבריה", "זלטופולסקי 18 תל-אביב"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select distribution location");
            builder.setItems(locations, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int position) {
                    switch (position) {
                        case 0:
                            txtDistributionLocation.setText("בית הדפוס 9 ירושלים");
                            break;
                        case 1:
                            txtDistributionLocation.setText("אהרון בראנד 14 ירושלים");
                            break;
                        case 2:
                            txtDistributionLocation.setText("שלמה המלך 4 טבריה");
                            break;
                        case 3:
                            txtDistributionLocation.setText("זלטופולסקי 18 תל-אביב");
                            break;
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        if (v == buttonStatus) {
            String parcelWeight[] = {"SENT", "OFFERED TO PICK UP", "ON ITS WAY", "RECEIVED"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Parcel Status");
            builder.setItems(parcelWeight, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int position) {
                    switch (position) {
                        case 0:
                            txtStatus.setText("SENT");
                            break;
                        case 1:
                            txtStatus.setText("OFFERED TO PICK UP");
                            break;
                        case 2:
                            txtStatus.setText("ON ITS WAY");
                            break;
                        case 3:
                            txtStatus.setText("RECEIVED");
                            break;
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        if (v == buttonAddParcel) {
            String id = txtId.getText().toString();
            String parcelType = txtParcelType.getText().toString();
            String fragile = txtFragile.getText().toString();
            String parcelWeight = txtParcelWeight.getText().toString();
            String location = txtDistributionLocation.getText().toString();
            String firstName = txtFName.getText().toString();
            String lastName = txtLName.getText().toString();
            String address = txtAddress.getText().toString();
            String sentDate = txtSentDate.getText().toString();
            String sentTime = txtSentTime.getText().toString();
            String receivedDate = txtDate.getText().toString();
            String receivedTime = txtTime.getText().toString();
            String PhoneNumber = txtPhoneNumber.getText().toString();
            String email = txtEmail.getText().toString();
            String parcelStatus = txtStatus.getText().toString();
            String deliveryManName = txtDeliveryManName.getText().toString();
            // first check that user filled out all the fields
            if (id.matches("") || parcelType.matches("") || fragile.matches("") || parcelWeight.matches("") || location.matches("") || firstName.matches("") || lastName.matches("") || address.matches("") || sentDate.matches("") || sentTime.matches("") || receivedDate.matches("") || receivedTime.matches("") || PhoneNumber.matches("") || email.matches("") || parcelStatus.matches("") || deliveryManName.matches(""))
                Toast.makeText(this, "You did not fill out all the fields", Toast.LENGTH_SHORT).show();
            else {
                buttonAddParcel.setEnabled(false);

                String id1 = txtId.getText().toString();
                ParcelType parcelType1;
                boolean fragile1;
                ParcelWeight parcelWeight1;
                //Location location1 = getLocationFromString(String.valueOf(txtDistributionLocation.getText()));
                String location1 = txtDistributionLocation.getText().toString();
                String firstName1 = txtFName.getText().toString();
                String lastName1 = txtLName.getText().toString();
                String address1 = txtAddress.getText().toString();
                String sentDate1 = txtSentDate.getText().toString();
                String sentTime1 = txtSentTime.getText().toString();
                String receivedDate1 = txtDate.getText().toString();
                String receivedTime1 = txtTime.getText().toString();
                String PhoneNumber1 = txtPhoneNumber.getText().toString();
                String email1 = txtEmail.getText().toString();
                ParcelStatus parcelStatus1;
                String deliveryManName1 = txtDeliveryManName.getText().toString();

                if (txtFragile.getText().toString() == "YES")
                    fragile1 = false;
                else
                    fragile1 = true;



                if (txtParcelWeight.getText().toString() == "up to 500 g")
                    parcelWeight1 = ParcelWeight.UP_TO_500_GRAM;
                else {
                    if (txtParcelWeight.getText().toString() == "up to 1 kg")
                        parcelWeight1 = ParcelWeight.UP_TO_1_KGRAM;
                    else {
                        if (txtParcelWeight.getText().toString() == "up to 5 kg")
                            parcelWeight1 = ParcelWeight.UP_TO_5_KGRAM;
                        else
                            parcelWeight1 = ParcelWeight.UP_TO_20_KGRAM;

                    }
                }



                if (txtParcelType.getText().toString() == "envelope")
                    parcelType1 = ParcelType.ENVELOPE;
                else {
                    if (txtParcelType.getText().toString() == "small package")
                        parcelType1 = ParcelType.SMALL_PACKAGE;
                    else
                        parcelType1 = ParcelType.BIG_PACKAGE;

                }



                if (txtStatus.getText().toString() == "RECEIVED")
                    parcelStatus1 = ParcelStatus.RECEIVED;
                else {
                    if (txtStatus.getText().toString() == "OFFERED TO PICK UP")
                        parcelStatus1 = ParcelStatus.OFFERED_TO_PICK_UP;
                    else {
                        if (txtStatus.getText().toString() == "OFFERED TO PICK UP")
                            parcelStatus1 = ParcelStatus.OFFERED_TO_PICK_UP;
                        else {
                            if (txtStatus.getText().toString() == "ON ITS WAY")
                                parcelStatus1 = ParcelStatus.ON_ITS_WAY;
                            else
                                parcelStatus1 = ParcelStatus.SENT;
                        }
                    }

                }

                Parcel parcelToAdd = new Parcel(id1, parcelType1, fragile1, parcelWeight1,location1, firstName1,lastName1, address1, sentDate1, receivedDate1, PhoneNumber1, email1, parcelStatus1, deliveryManName1);
                try {
                    parcelViewModel.addParcelToFirebase(parcelToAdd);
                } catch (Exception exception) {
                    Toast toast = Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                }
                ;
            }
        }
    }





    public Location getLocationFromString(String strAddress) {

        //    Geocoder coder = new Geocoder(this);
        Geocoder coder = new Geocoder(this,  new Locale("he"));
        List<Address> address;

        try {
            if (strAddress == "בית הדפוס 9 ירושלים") {
                double lat =31.785882;
                double lng = 35.190643;
                Location l=new Location("");
                l.setLatitude(lat);
                l.setLongitude(lng);
                return l;
            }
            if (strAddress == "אהרון בראנד 14 ירושלים") {
                double lat = 31.784904;
                double lng =35.172060 ;
                Location l=new Location("");
                l.setLatitude(lat);
                l.setLongitude(lng);
                return l;
            }

            if (strAddress == "שלמה המלך 4 טבריה") {
                double lat =32.793337;
                double lng =  35.523046;
                Location l=new Location("");
                l.setLatitude(lat);
                l.setLongitude(lng);
                return l;
            }
            else {          //if"זלטופולסקי 18 תל-אביב"
                double lat = 32.089086;
                double lng = 34.772722;
                Location l=new Location("");
                l.setLatitude(lat);
                l.setLongitude(lng);
                return l;

            }
        } catch (Exception e) {
            Toast.makeText(this, "you entered an incorrect address", Toast.LENGTH_SHORT).show();
            return null;
        }
    }



   /* public Location getLocationFromString(String strAddress) {

        //    Geocoder coder = new Geocoder(this);
        Geocoder coder = new Geocoder(this,  new Locale("he"));
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Location l=new Location("");
            l.setLatitude(lat);
            l.setLongitude(lng);
            return l;
        } catch (Exception e) {
            Toast.makeText(this, "you entered an incorrect address", Toast.LENGTH_SHORT).show();
            return null;
        }
    }*/






}