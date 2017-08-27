package com.example.abis.justjava2;


import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int co=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view) {
        //display((String) view);
        TextView q = (TextView) findViewById(R.id.quantity_text_view);
        int mul=Integer.parseInt(q.getText().toString());
        display(mul);
        displayPrice( mul);
    }
   public  void increment(View view)
   {
       TextView q = (TextView) findViewById(R.id.quantity_text_view);
       int mul=Integer.parseInt(q.getText().toString());
       if(mul<10){
       ++mul;
       display(mul);}
   }
   public void decrement(View view)
   {
       TextView q = (TextView) findViewById(R.id.quantity_text_view);
       int mul=Integer.parseInt(q.getText().toString());
       if(mul>1){
       --mul;
       display(mul);}
   }
    /**
     * This method displays the given quantity value on the screen.
     */
        private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayPrice(int quantity) {
        int number=5;
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        EditText name=(EditText) findViewById(R.id.editText);
        String Name1=name.getText().toString();
        String s="Name: "+Name1+ "\nQuantity:"+ quantity+"\n"+"whipped Cream added?  ";
        CheckBox whippedCream=(CheckBox) findViewById(R.id.whipped_cream);
        boolean b=whippedCream.isChecked();
        if(b)
            ++number;
        s+=b+"\n";
        CheckBox Chocolate=(CheckBox) findViewById(R.id.choco);
         b=Chocolate.isChecked();
        if(b)
            number+=2;
        s+="Chocolate added?  "+ b+ "\n";
        number*=quantity;
        s= (s+"Total"+"="+number+" $\nThank You");
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        Intent intent1 = intent.setData(Uri.parse("mailto:"));
        //intent1.putExtra(Intent.EXTRA_EMAIL, Name1);
        intent1.putExtra(Intent.EXTRA_SUBJECT, "order coffee");
        intent1.putExtra(android.content.Intent.EXTRA_EMAIL, Name1+"@gmail.com");
        intent1.putExtra(Intent.EXTRA_BCC, Name1);
        intent1.putExtra(Intent.EXTRA_TEXT, s);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent1);

        }
        priceTextView.setText(s);
    }
}