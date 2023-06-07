package com.flutterwave.raveandroid.rave_presentation.account;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.flutterwave.raveandroid.rave_core.models.Bank;

import java.util.List;

public interface AccountPaymentCallback {
    /**
     * Passes a list of banks available for this payment method.
     *
     * @param banks List of banks.
     */
    void onBanksListRetrieved(List<Bank> banks);

    /**
     * Called when there is an issue retrieving the list of banks.
     *
     * @param message
     */
    void onGetBanksRequestFailed(String message);

    /**
     * Called to indicate that a background task is running, e.g. a network call.
     * This should typically show or hide a progress bar.
     *
     * @param active If true, background task is running. If false, background task has stopped
     */
    void showProgressIndicator(boolean active);

    /**
     * Called to trigger an otp collection. The OTP should be collected from the user and passed to
     * the PaymentManager using {@link AccountPaymentManager#submitOtp(String)} to continue the payment.
     */
    void collectOtp(String message);

    /**
     * Called to display a {@link android.webkit.WebView} for charges that require webpage authentication.
     * When the payment is completed, the authentication page redirects to a {@link com.flutterwave.raveandroid.rave_java_commons.RaveConstants#RAVE_3DS_CALLBACK predefined url}
     * with the payment details appended to the url.
     * <p>
     * You should override the webview client's {@link android.webkit.WebViewClient#shouldOverrideUrlLoading(WebView, WebResourceRequest)} shouldOverrideUrlLoading}
     * function to check if the {@link WebResourceRequest#getUrl() url being loaded} contains the
     * {@link com.flutterwave.raveandroid.rave_java_commons.RaveConstants#RAVE_3DS_CALLBACK predefined redirect url}.
     * <p>
     * If it does, it means the transaction has been completed and you can now call {@link AccountPaymentManager#onWebpageAuthenticationComplete()} to check the transaction status.
     *
     * @param authenticationUrl The url to the authentication page
     */
    void showAuthenticationWebPage(String authenticationUrl);

    /**
     * Called when an error occurs with the payment. The error message can be displayed to the users.
     *
     * @param errorMessage A message describing the error
     * @param flwRef       The Flutterwave reference to the transaction.
     */
    void onError(String errorMessage, @Nullable String flwRef);

    /**
     * Called when the transaction has been completed successfully.
     *
     * @param flwRef The Flutterwave reference to the transaction.
     */
    void onSuccessful(String flwRef);

}