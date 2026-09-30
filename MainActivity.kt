package com.example.halamantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.halamantiket.ui.theme.HalamanTiketTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HalamanTiketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFA8D5BA) // Latar belakang abu-abu sangat muda/hijau lembut
                ) {
                    TicketBookingScreen()
                }
            }
        }
    }
}

@Composable
fun TicketBookingScreen() {
    val hargaPerTiket = 25000
    var jumlahTiket by remember { mutableIntStateOf(1) }

    val totalHarga = hargaPerTiket * jumlahTiket

    // Format Rupiah tanpa spasi tambahan (contoh: Rp25.000)
    val formatter = NumberFormat.getIntegerInstance(Locale.forLanguageTag("id-ID"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // --- HEADER BIRU ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E88E5)) // Biru Header
                .padding(top = 40.dp, bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ikon Tiket
                Icon(
                    imageVector = Icons.Default.ConfirmationNumber,
                    contentDescription = "Ikon Tiket",
                    tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Pemesanan Tiket",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Pesan tiket dengan mudah!",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        // --- KONTEN / CARD CONTAINER ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // CARD 1: HARGA TIKET
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Harga Tiket",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Rp${formatter.format(hargaPerTiket)}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF1E88E5) // Warna biru harga
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "per tiket",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // CARD 2: JUMLAH TIKET
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Jumlah Tiket",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Tombol Minus (-)
                        IconButton(
                            onClick = { if (jumlahTiket > 1) jumlahTiket-- },
                            enabled = jumlahTiket > 1,
                            modifier = Modifier.size(52.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = if (jumlahTiket > 1) Color(0xFF1E88E5) else Color.LightGray,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Kurang Tiket",
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        // Display Angka Jumlah
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .padding(horizontal = 16.dp)
                                .background(Color(0xFFF2F4F7), shape = RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$jumlahTiket",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                        // Tombol Plus (+)
                        IconButton(
                            onClick = { jumlahTiket++ },
                            modifier = Modifier.size(52.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color(0xFF1E88E5),
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Tambah Tiket",
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }

            // CARD 3: TOTAL BAYAR
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Rp${formatter.format(totalHarga)}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF2E7D32) // Warna hijau total bayar
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // TOMBOL RESET
            Button(
                onClick = { jumlahTiket = 1 },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEF5350), // Merah Reset
                    contentColor = Color.White
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reset Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "RESET",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}