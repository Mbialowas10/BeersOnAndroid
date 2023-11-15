package com.mbialowas.beersonandroid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mbialowas.beersonandroid.model.BeerItem
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun BeerCardDetails(
    beerItem: BeerItem,
    navController: NavController

){
    var isIconChanged by remember { mutableStateOf(false) }
    Column(modifier = Modifier

        .border(1.dp, Color.Red, shape = RectangleShape)
        .padding(5.dp)
        //.size(60.dp)


    ) {

        Row(
            modifier = Modifier
                //.border(2.dp, Color.Black, shape = CircleShape)
                .background(color = Color.DarkGray)
                .fillMaxWidth()
                //.height(80.dp)
                .padding(5.dp)
        ){
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                //painter = painterResource(id = R.drawable.bluemoon),
                painter = rememberImagePainter(data=beerItem.image),
                contentDescription = "Beer",

                )
            Column {
                Text(
                    color = Color.White,
                    text = beerItem.name,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp),
                    style = TextStyle(fontSize = 24.sp),
                    maxLines = 1
                ) // end Text
                Text(
                    text = beerItem.price,
                    modifier = Modifier.padding(end = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Column {
                    Row {
                        Text(
                            text = "Average Vote: " + BigDecimal(beerItem.rating.average).setScale(2,
                                RoundingMode.HALF_UP).toString() + "/5",
                            modifier = Modifier.padding(end = 8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Text(
                            text = "# of Reviews " + beerItem.rating.reviews.toString(),
                            modifier = Modifier.padding(end = 8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Button(
                            onClick = {
                                isIconChanged = !isIconChanged

                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .scale(2.5f),
                                imageVector = if (isIconChanged) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Add a favorite"
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
} // end BeerCard Composable