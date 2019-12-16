package hu.dornyayse.liveresultat_viewer.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hu.dornyayse.liveresultat_viewer.R;

public class SearchCompetitionDialogFragment extends DialogFragment {

    public static final String TAG = "SearchCompetitionDialogFragment";

    public interface SearchCompetitionDialogListener {
        void onShoppingItemCreated(CompetitionSearch search);
    }

    private SearchCompetitionDialogListener listener;

    private EditText name;
    private EditText date;
    private Switch searchSwitch;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof SearchCompetitionDialogListener) {
            listener = (SearchCompetitionDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the" +
                    " SearchCompetitionDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.search)
                .setView(getContentView())
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO implement item creation
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_search_competition, null);
        name = contentView.findViewById(R.id.name);
        date = contentView.findViewById(R.id.date);
        name.setEnabled(true);
        date.setEnabled(false);
        searchSwitch = contentView.findViewById(R.id.search_switch);
        searchSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    name.setEnabled(false);
                    date.setEnabled(true);
                    name.setText("");
                } else {
                    name.setEnabled(true);
                    date.setEnabled(false);
                    date.setText("");
                }
            }
        });

        DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return contentView;
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }

    public static class CompetitionSearch {
        SearchEnum searchEnum;
        Date date;
        String name;

        private enum SearchEnum {
            name,
            date
        }
    }
}