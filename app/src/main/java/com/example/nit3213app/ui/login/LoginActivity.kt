package com.example.nit3213app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nit3213app.databinding.ActivityLoginBinding
import com.example.nit3213app.ui.dashboard.DashboardActivity
import com.example.nit3213app.viewmodel.LoginResult
import com.example.nit3213app.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetAddress

/**
 * Login Activity that handles user authentication
 * 
 * This activity uses MVVM architecture with:
 * - ViewBinding for view references
 * - ViewModel for business logic
 * - LiveData for reactive UI updates
 * - Hilt for dependency injection
 */
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    
    // ViewBinding instance for accessing views
    private lateinit var binding: ActivityLoginBinding
    
    // ViewModel instance injected by Hilt
    private val viewModel: LoginViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set up UI components
        setupUI()
        
        // Observe ViewModel data
        observeViewModel()
        
        // Test network connectivity
        testNetworkConnectivity()
    }
    
    /**
     * Test network connectivity to debug DNS issues
     */
    private fun testNetworkConnectivity() {
        lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    try {
                        // Test basic DNS resolution
                        val googleIp = InetAddress.getByName("google.com")
                        Log.d("NetworkTest", "Google DNS resolved: ${googleIp.hostAddress}")
                        
                        // Test our API DNS resolution
                        val apiIp = InetAddress.getByName("nit3213api.onrender.com")
                        Log.d("NetworkTest", "API DNS resolved: ${apiIp.hostAddress}")
                        
                        "DNS Resolution working"
                    } catch (e: Exception) {
                        Log.e("NetworkTest", "DNS Resolution failed: ${e.message}")
                        "DNS Resolution failed: ${e.message}"
                    }
                }
                Log.d("NetworkTest", result)
            } catch (e: Exception) {
                Log.e("NetworkTest", "Network test error: ${e.message}")
            }
        }
    }
    
    /**
     * Set up UI components and click listeners
     */
    private fun setupUI() {
        // Set login button click listener
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            
            // Call ViewModel to perform login
            viewModel.login(username, password)
        }
    }
    
    /**
     * Observe ViewModel LiveData for UI updates
     */
    private fun observeViewModel() {
        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.buttonLogin.isEnabled = !isLoading
        }
        
        // Observe login result
        viewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginResult.Success -> {
                    // Hide error message
                    binding.textViewError.visibility = View.GONE
                    
                    // Navigate to Dashboard with keypass
                    navigateToDashboard(result.keypass)
                    
                    // Clear the result to prevent re-navigation
                    viewModel.clearLoginResult()
                }
                is LoginResult.Error -> {
                    // Show error message
                    binding.textViewError.text = result.message
                    binding.textViewError.visibility = View.VISIBLE
                    
                    // Also show toast for better UX
                    Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
                }
                null -> {
                    // Hide error message when result is cleared
                    binding.textViewError.visibility = View.GONE
                }
            }
        }
    }
    
    /**
     * Navigate to Dashboard Activity
     * 
     * @param keypass The authentication key to pass to dashboard
     */
    private fun navigateToDashboard(keypass: String) {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("keypass", keypass)
        }
        startActivity(intent)
        
        // Finish login activity so user can't go back
        finish()
    }
} 