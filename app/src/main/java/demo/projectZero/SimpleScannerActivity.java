package demo.projectZero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Edward on 2018/3/9.
 */

public class SimpleScannerActivity extends Activity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
        mScannerView.setAutoFocus(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
//        Log.w("result", rawResult.getContents()); // Prints scan results
//        Log.w("result getName", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
        Intent i = new Intent();
        i.putExtra("barCode", rawResult.getContents());
        setResult(RESULT_OK, i);
        finish();
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
