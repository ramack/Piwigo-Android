/*
 * Piwigo for Android
 * Copyright (C) 2016-2018 Piwigo Team http://piwigo.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.piwigo.internal.binding.adapter;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.piwigo.R;
import org.piwigo.accounts.UserManager;

import java.util.List;

import javax.inject.Inject;


public class AccountSelectionSpinnerAdapter extends ArrayAdapter<Account> {
    private LayoutInflater flater;
    @Inject
    protected UserManager userManager;

    public AccountSelectionSpinnerAdapter(@NonNull Activity context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Account> objects) {
        super(context, resource, objects);
        flater = context.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = getDropDownView(position, convertView, parent);
        /* so far we use exactly the same view for the closed spinner and in the dropdown but we change the colors */

        TextView username = (TextView) rowView.findViewById(R.id.aspin_username);
        TextView galleryUrlView = (TextView) rowView.findViewById(R.id.aspin_url);

        username.setTextColor(ContextCompat.getColor(getContext(), R.color.nav_selected_user));
        galleryUrlView.setTextColor(ContextCompat.getColor(getContext(), R.color.nav_selected_user));
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Account account = getItem(position);
        View rowView = convertView;

        if(rowView == null){
            rowView = flater.inflate(R.layout.account_selection_spinner_item, parent, false);
        }

        TextView username = (TextView) rowView.findViewById(R.id.aspin_username);
        username.setText(userManager.getUsername(account));
        if(userManager.isGuest(account)) {
            username.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
        }else{
            username.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }

        TextView galleryUrlView = (TextView) rowView.findViewById(R.id.aspin_url);
        galleryUrlView.setText(userManager.getSiteUrl(account));

        ImageView iconView = (ImageView) rowView.findViewById(R.id.aspin_image);
/*      TODO: show gallery image
         iconView.setImageResource(rowItem.);
 */

        /* TODO: check if we could replace the account_selection_spinner_item by account_row */

        return rowView;
    }
}
