package com.mbialowas.beersonandroid.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.mbialowas.beersonandroid.R
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.model.BeerItem

@Composable
fun Beers(beersManager: BeersManager){
    val beers = beersManager.beersResponse.value
    Log.d("beers", "$beers")
    val name:String
    val image:String
    val id:String
    val price:String

    for (beer in beers){
        Log.i("name", "${beer.name}")
    }
    LazyColumn{
        items(beers){beer->
            BeerCard(beerItem = beer)
        }
    }

//    Column {
//        BeerCard(name="Bud Light")
//
//        BeerCard(name="Geeko")
//        BeerCard(name="Mike")
//    }



}

@Composable
fun BeerCard(
    beerItem: BeerItem
){
    Column(modifier = Modifier

        .border(2.dp, Color.Red, shape = RectangleShape)
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
            }
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
} // end BeerCard Composable