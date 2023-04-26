// Team Kakashi
val naruto = NarutoCharacter("Naruto", mutableMapOf("Schattendoppelgänger" to 20))
val sasuke = NarutoCharacter("Sasuke", mutableMapOf("" to 20))
val sakura = NarutoCharacter("Sakura", mutableMapOf("heulen" to 20))

val teamKakashi = listOf(naruto.name, sasuke.name, sakura.name)

// Team Asuma
val shikamaru = NarutoCharacter("Shikamaru", mutableMapOf("Schattenfessel" to 30))
val choji = NarutoCharacter("Choji", mutableMapOf("Fleischbombenpanzer" to 20))
val ino = NarutoCharacter("Ino", mutableMapOf("" to 20))

val teamAsuma = listOf(shikamaru.name, choji.name, ino.name)

// Team Kurenai
val hinata = NarutoCharacter("Hinata", mutableMapOf("" to 20))
val kiba = NarutoCharacter("Kiba", mutableMapOf("" to 20))
val shino = NarutoCharacter("Shino", mutableMapOf("" to 20))

val teamKurenai = listOf(hinata.name, kiba.name, shino.name)

// Team Mighty Guy
val rockLee = NarutoCharacter("Rock Lee", mutableMapOf("" to 20))
val neji = NarutoCharacter("Neji", mutableMapOf("" to 20))
val tenten = NarutoCharacter("Tenten", mutableMapOf("" to 20))

val teamGuy = listOf(rockLee.name, neji.name, tenten.name)

// Team aus Sunagakure
val gaara = NarutoCharacter("Gaara", mutableMapOf("" to 20))
val kankuro = NarutoCharacter("Kankuro", mutableMapOf("" to 20))
val temari = NarutoCharacter("Temari", mutableMapOf("" to 20))

val teamSunagakure = listOf(gaara.name, kankuro.name, temari.name)

// Einzelkämpfer
val kakashi = NarutoCharacter("Kakashi", mutableMapOf("" to 40))
val asuma = NarutoCharacter("Asuma", mutableMapOf("" to 40))
val kurenai = NarutoCharacter("Kurenai", mutableMapOf("" to 40))
val mightyGuy = NarutoCharacter("Mighty Guy", mutableMapOf("" to 40))
val minato = NarutoCharacter("Minato", mutableMapOf("" to 40))
val tsunade = NarutoCharacter("Tsunade", mutableMapOf("" to 40))
val jiraiya = NarutoCharacter("Jiraiya", mutableMapOf("" to 40))

// böse Charakter
val orochimaru = NarutoCharacter("Orochimaru", mutableMapOf("" to 40))
val zabuza = NarutoCharacter("Zabuza", mutableMapOf("" to 40))
val haku = NarutoCharacter("Haku", mutableMapOf("" to 40))
val kabuto = NarutoCharacter("Kabuto", mutableMapOf("" to 40))
val deidara = NarutoCharacter("Deidara", mutableMapOf("" to 40))
val pain = NarutoCharacter("Pain", mutableMapOf("" to 40))

val itachi = NarutoCharacter("Itachi", mutableMapOf("" to 40))
val kisame = NarutoCharacter("Kisame", mutableMapOf("" to 40))
val itachiAndKisame = listOf(itachi.name, kisame.name)

val zouri = NarutoCharacter("Zouri", mutableMapOf("" to 30),)
val waraji = NarutoCharacter("Waraji", mutableMapOf("" to 30))
val zouriAndWaraji = listOf(zouri.name, waraji.name)

val characterList = listOf(
    naruto.name, sasuke.name, sakura.name,
    shikamaru.name, choji.name, ino.name,
    hinata.name, kiba.name, shino.name,
    rockLee.name, neji.name, tenten.name,
    gaara.name, kankuro.name, temari.name,
    kakashi.name, asuma.name, kurenai.name, mightyGuy.name, minato.name, tsunade.name, jiraiya.name,
    orochimaru.name, zabuza.name, haku.name, kabuto.name, deidara.name, pain.name,
    )

val teamList = listOf("Team Kakashi", "Team Asuma", "Team Kurenai", "Team Guy", "Team Sunagakure", "Itachi und Kisame", "Zouri und Waraji")



fun main() {


    val zufall = (teamList + characterList).random()

        nurSo(zufall)

}

fun nurSo(zufall: String) {



}
