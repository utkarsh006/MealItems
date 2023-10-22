package com.example.meal.presentation.meal_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meal.domain.model.Meal
import com.example.meal.presentation.meal_list.components.MealListItem

@Composable
fun MealDetailScreen(
    meal: Meal
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MealListItem(meal = meal, onItemClicked = {})

        Spacer(Modifier.height(6.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = meal.strInstructions,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Magenta
                )
            )
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun MealDetailScreenUIPreview() {
//    MealDetailScreen(
//        meal = Meal(
//            "12",
//            "Chicken Handi",
//            "\"https://www.themealdb.com/images/media/meals/wyxwsp1486979827.jpg\"",
//            "STEP 1 - MARINATING THE CHICKEN\\r\\nIn a bowl, add chicken, salt, white pepper, ginger juice and then mix it together well.\\r\\nSet the chicken aside.\\r\\nSTEP 2 - RINSE THE WHITE RICE\\r\\nRinse the rice in a metal bowl or pot a couple times and then drain the water.\\r\\nSTEP 2 - BOILING THE WHITE RICE\\r\\nNext add 8 cups of water and then set the stove on high heat until it is boiling. Once rice porridge starts to boil, set the stove on low heat and then stir it once every 8-10 minutes for around 20-25 minutes.\\r\\nAfter 25 minutes, this is optional but you can add a little bit more water to make rice porridge to make it less thick or to your preference.\\r\\nNext add the marinated chicken to the rice porridge and leave the stove on low heat for another 10 minutes.\\r\\nAfter an additional 10 minutes add the green onions, sliced ginger, 1 pinch of salt, 1 pinch of white pepper and stir for 10 seconds.\\r\\nServe the rice porridge in a bowl\\r\\nOptional: add Coriander on top of the rice porridge.\""
//        ),
//        onItemClicked = {}
//    )
// }
