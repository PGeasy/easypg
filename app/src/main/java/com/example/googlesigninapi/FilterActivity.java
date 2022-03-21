package com.example.googlesigninapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterActivity extends AppCompatActivity {

    String localityCheckCode="000";
    String collegeCheckCode="111";
    String rentCheckCode="222";
    private static final String TAG ="FilterTestCode" ;
    ListView detailsListView,rightList;
    ArrayList<String> detailsList;
    ArrayList<Filter_POJO> localityList,collegeList,rentList;
    Button filterApplyBtn;
    CheckBox checkBox;
    Toolbar toolbar;
    String[] localityListFromResources,collegeListFromResources,rentListFromResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        detailsList = new ArrayList<String>(Arrays.asList("Locality","College","Rent"));
        checkBox= (CheckBox) findViewById(R.id.filter_chkbox);
        filterApplyBtn= (Button) findViewById(R.id.filter_apply_btn);
        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        setSupportActionBar(toolbar);
        localityListFromResources=getResources().getStringArray(R.array.locality);
        collegeListFromResources=getResources().getStringArray(R.array.institutes);
        rentListFromResources=getResources().getStringArray(R.array.rent);
        localityList = new ArrayList<Filter_POJO>();
        collegeList = new ArrayList<Filter_POJO>();
        rentList = new ArrayList<Filter_POJO>();
        for(int i=0;i<localityListFromResources.length;i++)
        {
            localityList.add(new Filter_POJO(localityListFromResources[i]));
        }
        for(int i=0;i<collegeListFromResources.length;i++)
        {
            collegeList.add(new Filter_POJO(collegeListFromResources[i]));
        }
        for(int i=0;i<rentListFromResources.length;i++)
        {
            rentList.add(new Filter_POJO(rentListFromResources[i]));
        }
        detailsListView = (ListView) findViewById(R.id.list_view_left);
        rightList= (ListView) findViewById(R.id.list_view_right);
        DetailsListAdapter listAdapter = new DetailsListAdapter(detailsList);
        detailsListView.setAdapter(listAdapter);

        detailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {

                if(position == 0)
                {
                    RightListDetailsAdapter adapter=new RightListDetailsAdapter(localityList);
                    rightList.setAdapter(adapter);
                }

                else if(position == 1)
                {
                    RightListDetailsAdapter adapter=new RightListDetailsAdapter(collegeList);
                    rightList.setAdapter(adapter);
                }

                else if(position==2)
                {
                    RightListDetailsAdapter adapter=new RightListDetailsAdapter(rentList);
                    rightList.setAdapter(adapter);
                }
            }
        });


        rightList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Filter_POJO ob= (Filter_POJO) adapterView.getItemAtPosition(position);
                Log.d(TAG,"tick"+ob.getName());
            }
        });

        filterApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ArrayList<String> filteredNameofLocality=new ArrayList<String>();
                for(int i = 0; i < localityList.size(); i++)
                {
                    Filter_POJO ob = localityList.get(i);
                    if(ob.isChecked())
                    {
                        filteredNameofLocality.add(ob.getName());
                        Log.d(TAG,"checked:"+ob.getName()+"\n");
                    }
                }
                ArrayList<String> filteredNameofColleges=new ArrayList<String>();
                for(int i = 0; i < collegeList.size(); i++)
                {
                    Filter_POJO ob = collegeList.get(i);
                    if(ob.isChecked())
                    {
                        filteredNameofColleges.add(ob.getName());
                        Log.d(TAG,"checked:"+ob.getName()+"\n");
                    }
                }
                ArrayList<String> filteredNameofRent=new ArrayList<String>();
                for(int i = 0; i < rentList.size(); i++)
                {
                    Filter_POJO ob = rentList.get(i);
                    if(ob.isChecked())
                    {
                        filteredNameofRent.add(ob.getName());
                        Log.d(TAG,"checked:"+ob.getName()+"\n");
                    }
                }

                if(filteredNameofLocality.isEmpty() && filteredNameofColleges.isEmpty() && filteredNameofRent.isEmpty())
                {
                    Toast.makeText(FilterActivity.this,"NO field is selected",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Intent intentToFindPgActivity_Filter = new Intent(getApplicationContext(), MainActivity.class);
                    intentToFindPgActivity_Filter.putExtra("source","FilterActivity");

                    if (!filteredNameofLocality.isEmpty())
                    {
                        localityCheckCode="0";
                        Log.d(TAG,localityCheckCode);
                        intentToFindPgActivity_Filter.putExtra("localityCheckCode",localityCheckCode);
                        intentToFindPgActivity_Filter.putStringArrayListExtra("filteredLocalityList", filteredNameofLocality);
                    }

                    if (!filteredNameofColleges.isEmpty())
                    {

                        collegeCheckCode="1";
                        Log.d(TAG,collegeCheckCode);
                        intentToFindPgActivity_Filter.putExtra("collegeCheckCode",collegeCheckCode);
                        intentToFindPgActivity_Filter.putStringArrayListExtra("filteredCollegesList",filteredNameofColleges);
                    }


                    if (!filteredNameofRent.isEmpty())
                    {

                        rentCheckCode="2";
                        Log.d(TAG,rentCheckCode);
                        intentToFindPgActivity_Filter.putExtra("rentCheckCode",rentCheckCode);
                        intentToFindPgActivity_Filter.putStringArrayListExtra("filteredRentList",filteredNameofRent);
                    }

                    startActivity(intentToFindPgActivity_Filter);
                }
            }
        });
    }


    private class RightListDetailsAdapter extends BaseAdapter
    {
        private ArrayList<Filter_POJO> list;


        public RightListDetailsAdapter()
        {

        }

        public RightListDetailsAdapter(ArrayList<Filter_POJO> list)
        {
            this.list=list;
        }


        class ExpandViewHolder
        {
            CheckBox filterCheckBox;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater l = getLayoutInflater();
            ExpandViewHolder holder=new ExpandViewHolder();
            if (convertView == null) {
                convertView = l.inflate(R.layout.filter_right_list_layout, null);
                holder.filterCheckBox = (CheckBox) convertView.findViewById(R.id.filter_chkbox);
                convertView.setTag(holder);



                holder.filterCheckBox.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Filter_POJO ob = (Filter_POJO) cb.getTag();
                        ob.setChecked(cb.isChecked());
                    }
                });



            } else {
                holder = (ExpandViewHolder) convertView.getTag();
            }

            Filter_POJO ob=list.get(position);
            holder.filterCheckBox.setText(ob.getName());
            holder.filterCheckBox.setChecked(ob.isChecked());
            holder.filterCheckBox.setTag(ob);
            return convertView;
        }
    }



    private class DetailsListAdapter extends BaseAdapter {

        private ArrayList<String> mDetails;

        public DetailsListAdapter(ArrayList<String> mDetails)
        {
            this.mDetails = mDetails;
        }


        class DetailsViewHolder
        {
            TextView filterOptions;
        }

        @Override
        public int getCount() {
            return mDetails.size();
        }

        @Override
        public Object getItem(int position) {
            return mDetails.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater li = getLayoutInflater();
            DetailsViewHolder detailsViewHolder = new DetailsViewHolder();

            if (convertView == null)
            {
                convertView = li.inflate(R.layout.filter_left_list_layout, null);
                detailsViewHolder.filterOptions = (TextView) convertView.findViewById(R.id.options_tv);
                convertView.setTag(detailsViewHolder);
            } else {
                detailsViewHolder = (DetailsViewHolder) convertView.getTag();
            }

            String ob= (String) getItem(position);
            detailsViewHolder.filterOptions.setText(ob);
            return convertView;
        }
    }
}
