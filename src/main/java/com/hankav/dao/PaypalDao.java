package com.hankav.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * @author Crunchify.com Version: 1.1.0
 * 
 */

public class PaypalDao {
	private static String crunchifyID = "AevFN2sNV6izA3cHtRyKiZuveRl5NOA-6Y5yqsbGjBQUFsqmABrYRCiTuyzKVqzIv317gyYMWyRnrUEe";
	private static String crunchifySecret = "ENRBVwjtEhM5DNLdpGwlxHyAQGXE_mN5D7mky7lNMo8XLcfDFH31nWHE0aKdxqBTRGFV-xV7HRhTA_8D";

	private static String executionMode = "live"; // sandbox or production

	// This is simple API call which will capture a specified amount for any given
	// Payer or User
	public Payment crunchifyCapturePayPalAPI(List<com.hankav.model.Item> itemstopaypal, String currency) {

		System.out.println("currency is" + currency);
		/*
		 * Flow would look like this: 1. Create Payer object and set PaymentMethod 2.
		 * Set RedirectUrls and set cancelURL and returnURL 3. Set Details and Add
		 * PaymentDetails 4. Set Amount 5. Set Transaction 6. Add Payment Details and
		 * set Intent to "authorize" 7. Create APIContext by passing the clientID,
		 * secret and mode 8. Create Payment object and get paymentID 9. Set payerID to
		 * PaymentExecution object 10. Execute Payment and get Authorization
		 * 
		 */
		String curcode = "";

		if (currency.equals("USD")) {
			curcode = "USD";
		} else if (currency.equals("GBP")) {
			curcode = "GBP";
		} else if (currency.equals("EUR")) {
			curcode = "EUR";
		}

		Payer crunchifyPayer = new Payer();
		crunchifyPayer.setPaymentMethod("paypal");

		// Redirect URLs
		RedirectUrls crunchifyRedirectUrls = new RedirectUrls();
		crunchifyRedirectUrls.setCancelUrl("https://www.eagletip.com/tipsters");
		crunchifyRedirectUrls.setReturnUrl("https://www.eagletip.com/PaypalReturnUrl");

		// Set Payment Details Object
		/*
		 * Details crunchifyDetails = new Details();
		 * crunchifyDetails.setShipping("2.22"); crunchifyDetails.setSubtotal("3.33");
		 * crunchifyDetails.setTax("1.11");
		 * 
		 * // Set Payment amount Amount crunchifyAmount = new Amount();
		 * crunchifyAmount.setCurrency("USD"); crunchifyAmount.setTotal("6.66");
		 * crunchifyAmount.setDetails(crunchifyDetails);
		 */

		// Set Transaction information

		Double totalamount = 0.0;

		// ### Items
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		Iterator<com.hankav.model.Item> itemsmaniterator = itemstopaypal.iterator();
		while (itemsmaniterator.hasNext()) {
			com.hankav.model.Item str = itemsmaniterator.next();
			Item item = new Item();
			item.setName(str.getName()).setQuantity("1").setCurrency(curcode).setPrice(str.getPrice().toString())
					.setDescription(str.getMonths().toString() + "---" + "month subscription");
			items.add(item);
			totalamount += Double.parseDouble(str.getPrice().toString());
		}

		itemList.setItems(items);

		Details details = new Details();
		details.setShipping("0");
		details.setSubtotal(totalamount.toString());
		details.setTax("0");

		// ###Amount
		// Let's you specify a payment amount.
		Amount amount = new Amount();
		amount.setCurrency(curcode);
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(totalamount.toString());
		System.out.println("totalam is" + totalamount.toString());
		amount.setDetails(details);

		// ###Transaction
		// A transaction defines the contract of a
		// payment - what is the payment for and who
		// is fulfilling it. Transaction is created with
		// a `Payee` and `Amount` types
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("This is the payment transaction description.");

		transaction.setItemList(itemList);
		List<Transaction> crunchifyTransactions = new ArrayList<Transaction>();
		crunchifyTransactions.add(transaction);

		// Add Payment details
		Payment crunchifyPayment = new Payment();

		// Set Payment intent to authorize
		crunchifyPayment.setIntent("sale");
		crunchifyPayment.setPayer(crunchifyPayer);
		crunchifyPayment.setTransactions(crunchifyTransactions);
		crunchifyPayment.setRedirectUrls(crunchifyRedirectUrls);

		// Pass the clientID, secret and mode. The easiest, and most widely used option.
		APIContext crunchifyapiContext = new APIContext(crunchifyID, crunchifySecret, executionMode);

		Payment myPayment = null;
		try {

			myPayment = crunchifyPayment.create(crunchifyapiContext);

			/*
			 * System.out.println("createdPayment Obejct Details ==> " +
			 * myPayment.toString());
			 */

			// Identifier of the payment resource created
			crunchifyPayment.setId(myPayment.getId());

			// Create payment

			/*
			 * PaymentExecution crunchifyPaymentExecution = new PaymentExecution();
			 * 
			 * // Set your PayerID. The ID of the Payer, passed in the `return_url` by
			 * PayPal. crunchifyPaymentExecution.setPayerId("spanishbo@gmail.com");
			 * 
			 * // This call will fail as user has to access Payment on UI. Programmatically
			 * // there is no way you can get Payer's consent. Payment createdAuthPayment =
			 * crunchifyPayment.execute(crunchifyapiContext, crunchifyPaymentExecution);
			 * 
			 * // Transactional details including the amount and item details. Authorization
			 * crunchifyAuthorization =
			 * createdAuthPayment.getTransactions().get(0).getRelatedResources().get(0).
			 * getAuthorization();
			 * 
			 * log("Here is your Authorization ID" + crunchifyAuthorization.getId());
			 */

		} catch (PayPalRESTException e) {

			// The "standard" error output stream. This stream is already open and ready to
			// accept output data.
			System.err.println(e.getDetails());
		}
		return myPayment;
	}

}
