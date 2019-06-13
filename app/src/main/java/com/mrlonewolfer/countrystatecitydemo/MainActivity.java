package com.mrlonewolfer.countrystatecitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAsyncTaskAdapter.OnResponseListner{

    private static final String TAG ="mycountry" ;
    Spinner spinnerCountry,spinnerRegion,spinnerCity;
    CountryBean countryBeanFile;
    RegionBean regionBeanFile;
    CityBean cityBeanFile;
    Context context;
    ArrayList<CountryBean> countryArrayList;
    ArrayList<RegionBean> regionArrayList;
    ArrayList<CityBean> cityArrayList;
    String spinnerType="Country";
    String countrycode,regioncode;

    TextView txtLatitude,txtLongitude;

    String countryUrl,regionUrl,cityUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCountry=findViewById(R.id.spinnerCountry);
        spinnerRegion=findViewById(R.id.spinnerRegion);
        spinnerCity=findViewById(R.id.spinnerCity);
        txtLatitude=findViewById(R.id.txtLatitude);
        txtLongitude=findViewById(R.id.txtLongitude);
        context=this;
        countryUrl="https://battuta.medunes.net/api/country/all/?key=00000000000000000000000000000000";

        retriveMyAsynTask(spinnerType);


        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerType="Region";
                countrycode=countryArrayList.get(position).getCountryCode();
                Toast.makeText(context, ""+countrycode, Toast.LENGTH_SHORT).show();
                regionUrl="https://battuta.medunes.net/api/region/"+countrycode+"/all/?key=00000000000000000000000000000000";
                Log.e(TAG, "regionurl: "+regionUrl );
                retriveMyAsynTask(spinnerType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerType="City";
                regioncode=regionArrayList.get(position).getRegioname();
                countrycode=regionArrayList.get(position).getCountycode();
                Toast.makeText(context, ""+regioncode, Toast.LENGTH_SHORT).show();
                cityUrl="https://battuta.medunes.net/api/city/"+countrycode+"/search/?region="+regioncode+"&key=00000000000000000000000000000000";
                Log.e(TAG, "regionurl: "+cityUrl );
                retriveMyAsynTask(spinnerType);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double latitude=cityArrayList.get(position).latitude;
                double longitude=cityArrayList.get(position).longitude;
                txtLatitude.setText(latitude+"");
                txtLongitude.setText(longitude+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void retriveMyAsynTask(String spinnerType) {
        MyAsyncTaskAdapter myAsyncTaskAdapter = new MyAsyncTaskAdapter(context, this, spinnerType);

        if(spinnerType.equals("Country")){
           // MyAsyncTaskAdapter myAsyncTaskAdapter = new MyAsyncTaskAdapter(context, this, spinnerType);
            myAsyncTaskAdapter.execute(countryUrl);
        }
        if(spinnerType.equals("Region")) {
         //   MyAsyncTaskAdapter myAsyncTaskAdapter = new MyAsyncTaskAdapter(context, this, spinnerType);
            myAsyncTaskAdapter.execute(regionUrl);
        }
        if(spinnerType.equals("City")) {
            //MyAsyncTaskAdapter myAsyncTaskAdapter = new MyAsyncTaskAdapter(context, this, spinnerType);
            myAsyncTaskAdapter.execute(cityUrl);
        }

    }


    @Override
    public void Response(String data,String spinnerType) {

        if (spinnerType.equals("Country")) {
            try {
                JSONArray jsonArray = new JSONArray(data);
                countryArrayList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String cName = jsonObject.getString("name");
                    String cCode = jsonObject.getString("code");
                    countryBeanFile = new CountryBean(cName, cCode);
                    countryArrayList.add(countryBeanFile);
                    Log.i(TAG, "CountryName: " + cName);
                }

                ArrayAdapter<CountryBean> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, countryArrayList);
                spinnerCountry.setAdapter(arrayAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(spinnerType.equals("Region")){
            try {
                JSONArray jsonArray = new JSONArray(data);
                regionArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String rName = jsonObject.getString("region");
                    String cCode = jsonObject.getString("country");
                    regionBeanFile = new RegionBean(rName, cCode);
                    regionArrayList.add(regionBeanFile);
                    Log.i(TAG, "RegionName: " + rName);
                }

                ArrayAdapter<RegionBean> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, regionArrayList);
                spinnerRegion.setAdapter(arrayAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        if(spinnerType.equals("City")){
            try {
                JSONArray jsonArray = new JSONArray(data);
                cityArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String cityName = jsonObject.getString("city");

                    String rName = jsonObject.getString("region");
                    String cCode = jsonObject.getString("country");
                    Double latitude = Double.valueOf(jsonObject.getString("latitude"));
                    Double longitude = Double.valueOf(jsonObject.getString("longitude"));
                    cityBeanFile = new CityBean(cityName,rName,cCode,latitude,longitude);
                    cityArrayList.add(cityBeanFile);
                    Log.i(TAG, "CityName: " + cityName);
                }

                ArrayAdapter<CityBean> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, cityArrayList);
                spinnerCity.setAdapter(arrayAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
