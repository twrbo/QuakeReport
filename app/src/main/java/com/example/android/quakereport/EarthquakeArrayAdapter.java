package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeArrayAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitude_TextView = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());
        magnitude_TextView.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude_TextView.getBackground();
        int magnitudeColor = getMagnutideColor(currentEarthquake.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String locationExplicit = currentEarthquake.getmCountry();
        String distance;
        String location;
        if (locationExplicit.contains("of")) {
            distance = locationExplicit.split("of")[0] + "of";
            location = locationExplicit.split("of")[1];
        } else {
            distance = getContext().getString(R.string.near_the);
            location = locationExplicit;
        }


        TextView distance_TextView = (TextView) listItemView.findViewById(R.id.location_offset);
        distance_TextView.setText(distance);

        TextView country = (TextView) listItemView.findViewById(R.id.primary_location);
        country.setText(location);

        Date dateObject = new Date(currentEarthquake.getmDate());
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);

        return listItemView;
    }

    private int getMagnutideColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    private String formatDate(Date dateObj) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(dateObj);
    }

    private String formatTime(Date dateObj) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(dateObj);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
