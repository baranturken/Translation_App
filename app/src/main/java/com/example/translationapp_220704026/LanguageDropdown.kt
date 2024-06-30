import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LanguageDropdown(
    languages: List<String>,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val languageMap = mapOf(
        "English" to "en",
        "Spanish" to "es",
        "French" to "fr",
        "German" to "de",
        "Simplified Chinese" to "zh-cn",
        "Traditional Chinese" to "zh-tw"
    )

    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { expanded.value = true }
                .fillMaxWidth()
        ) {
            Text(
                text = languageMap.filterValues { it == selectedLanguage }.keys.firstOrNull() ?: selectedLanguage,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Indicator"
            )
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            languageMap.forEach { (languageName, languageCode) ->
                DropdownMenuItem(
                    onClick = {
                        onLanguageSelected(languageCode)
                        expanded.value = false
                    },
                    text = { Text(languageName) }
                )
            }
        }
    }
}
