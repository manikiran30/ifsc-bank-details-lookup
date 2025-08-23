# IFSC Code Bank Details Lookup App

An Android application that fetches and displays detailed bank information using valid IFSC codes. Developed using Android Studio, this app interacts with a public API to provide real-time bank details in a simple and user-friendly interface.

## Features

- Search bank details using IFSC code
- Displays bank name, branch, address, city, and contact
- Error handling for invalid or empty IFSC inputs
- Fast and responsive UI with minimal design

## Tech Stack

- Android (Java/Kotlin)
- Retrofit or Volley for REST API calls
- Material Design UI components

## API Used

This app uses the following public API to fetch bank details:
RazorPay API
Endpoint: GET https://ifsc.razorpay.com/{IFSC_CODE}
Response: Bank name, branch, city, state, contact, UPI enabled status, etc.
Example usage documented in GeeksforGeeks
