package com.mojio.testsdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mojio.mojiosdk.models.Vehicle;

import java.util.ArrayList;

/**
 * Created by ssawchenko on 15-01-13.
 */
public class VehicleListAdapter extends ArrayAdapter<Vehicle> {

    public VehicleListAdapter(Context context, ArrayList<Vehicle> data) {
        super(context, 0, data); // Layout ID set in getView; pass 0 to default constructor.
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {

        // Get the data item for this position
        Vehicle vehicle = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.layout_vehicle_item, parent, false);
        }

        ((TextView) row.findViewById(R.id.v_title)).setText("ID: " + vehicle.VehicleName);
        ((TextView) row.findViewById(R.id.v_OwnerId)).setText("OwnerID: " + vehicle.OwnerId);
        ((TextView) row.findViewById(R.id.v_LicensePlate)).setText("LicensePlate: " + vehicle.LicensePlate);
        ((TextView) row.findViewById(R.id.v_LastContactTime)).setText("LastContactTime: " + vehicle.LastContactTime);
        ((TextView) row.findViewById(R.id.v_LastLocationTime)).setText("LastLocationTime: " + vehicle.LastLocationTime);

        return row;
    }
}
