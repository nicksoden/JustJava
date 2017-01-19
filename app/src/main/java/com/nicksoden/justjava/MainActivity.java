/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */
package com.nicksoden.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /*Creates whipped cream Checkbox object*/
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText editText = (EditText) findViewById(R.id.edit_text_name);
        String editTextName = editText.getText().toString();

        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream + editTextName);

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, editTextName);
        displayMessage(priceMessage);

        /*displayMessage(createOrderSummary(price));
        *
        * this is better than the above code
        */
    }

    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int multiplier = 5;
        /*
        change this to ...
        return quantity * 5;
        to be more precise
        */

        if (whippedCream) {
            multiplier += 1;
        }
        if (chocolate) {
            multiplier += 3;
            Log.v("MainActivity", "multiplier");
        }
        int price = quantity * multiplier;
        return price;
    }

    /**
     * Create summary of the order
     * @param price
     * @param hasWhippedCream is whether the user has whipped cream or not
     * @return text summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String editTextName){
        String priceMessage = getString(R.string.persons) + editTextName;
        priceMessage += "\n" + getString(R.string.added_whipped_cream) + hasWhippedCream;
        priceMessage += "\n" + getString(R.string.added_chocolate) + hasChocolate;
        priceMessage += "\n" + getString(R.string.quantity_ordered)+ quantity;
        priceMessage +=  "\n" + getString(R.string.total_price) + price;
        priceMessage +=  "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}
