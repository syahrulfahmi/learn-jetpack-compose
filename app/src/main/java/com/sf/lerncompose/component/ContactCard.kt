package com.sf.lerncompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sf.lerncompose.R
import com.sf.lerncompose.ui.theme.LearnJetpackComposeTheme


@Composable
fun ContactCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable(onClick = {})
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = "avatar",
                Modifier
                    .padding(4.dp)
                    .size(60.dp)
                    .border(2.dp, Color.Green, CircleShape)
                    .clip(
                        CircleShape
                    )
            )
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "online status",
                tint = Color.Green,
                modifier = Modifier.align(
                    Alignment.BottomEnd
                )
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Dico Wisesa", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
            Text(text = "Online")
        }
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            modifier = Modifier.offset(x = 8.dp, y = 30.dp)
        )
    }
}

@Preview
@Composable
private fun ContactCardPreview() {
    LearnJetpackComposeTheme {
        Surface {
            ContactCard()
        }
    }
}