package com.rktuhinbd.sslsandbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class MainActivity extends AppCompatActivity implements SSLCTransactionResponseListener {

    private PaymentGatewaySSLCommerz paymentGateway;

    private final String storeId = "mille605083ad07976";
    private final String password = "mille605083ad07976@ssl";
    private String transactionId = "123456789098765";
    private Integer amount = 1000;

    // = = = Declare customer info data properties = = = //
    private String name = "Md. Rejaul Karim";
    private String email = "rejaul.karim@mislbd.com";
    private String address = "House - 38, Road - 11, DIT Project, Badda";
    private String city = "Dhaka";
    private String postCode = "1212";
    private String country = "Bangladesh";
    private String phoneNumber = "01841752600";

    // = = = Declare product info data properties = = = //
    private String productName = "demoProduct";
    private String productCategory = "demoCategory";
    private String productProfile = "demoProfile";
    private String hotelName = "Grand Sultan";
    private String lengthOfStay = "3 days";
    private String checkInTime = "12pm";
    private String hotelCity = "Sylhet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paymentGateway = new PaymentGatewaySSLCommerz(this, this);

        paymentGateway.setSslCommerzInitialization(storeId, password, amount, transactionId, productCategory);
        paymentGateway.setCustomerInfoInitializer(name, email, address, city, postCode, country, phoneNumber);
        paymentGateway.setProductInitializer(productName, productCategory, productProfile, hotelName, lengthOfStay, checkInTime, hotelCity);

        paymentGateway.integrateSSLCommerz();
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {

        Log.e("Transaction API", sslcTransactionInfoModel.getAPIConnect() + "");
        Log.e("Transaction Status", sslcTransactionInfoModel.getStatus() + "");
        Log.e("Transaction Amount", sslcTransactionInfoModel.getAmount() + "");

    }

    @Override
    public void transactionFail(String s) {
        Log.e("Transaction Failure", s + "");
    }

    @Override
    public void merchantValidationError(String s) {
        Log.e("Merchant Error", s + "");
    }
}