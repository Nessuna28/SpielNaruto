
// Team Kakashi
val naruto = NarutoCharacter("Naruto")
val sasuke = NarutoCharacter("Sasuke")
val sakura = NarutoCharacter("Sakura")

val teamKakashi = listOf(naruto.name, sasuke.name, sakura.name)

// Team Asuma
val shikamaru = NarutoCharacter("Shikamaru")
val choji = NarutoCharacter("Choji")
val ino = NarutoCharacter("Ino")

val teamAsuma = listOf(shikamaru.name, choji.name, ino.name)

// Team Kurenai
val hinata = NarutoCharacter("Hinata")
val kiba = NarutoCharacter("Kiba")
val shino = NarutoCharacter("Shino")

val teamKurenai = listOf(hinata.name, kiba.name, shino.name)

// Team Mighty Guy
val rockLee = NarutoCharacter("Rock Lee")
val neji = NarutoCharacter("Neji")
val tenten = NarutoCharacter("Tenten")

val teamGuy = listOf(rockLee.name, neji.name, tenten.name)

// Team aus Sunagakure
val gaara = NarutoCharacter("Gaara")
val kankuro = NarutoCharacter("Kankuro")
val temari = NarutoCharacter("Temari")

val teamSunagakure = listOf(gaara.name, kankuro.name, temari.name)

// Einzelkämpfer
val kakashi = NarutoCharacter("Kakashi")
val asuma = NarutoCharacter("Asuma")
val kurenai = NarutoCharacter("Kurenai")
val mightyGuy = NarutoCharacter("Mighty Guy")
val minato = NarutoCharacter("Minato")
val tsunade = NarutoCharacter("Tsunade")
val jiraiya = NarutoCharacter("Jiraiya")

// böse Charakter
val orochimaru = NarutoCharacter("Orochimaru")
val zabuza = NarutoCharacter("Zabuza")
val haku = NarutoCharacter("Haku")
val kabuto = NarutoCharacter("Kabuto")
val deidara = NarutoCharacter("Deidara")
val pain = NarutoCharacter("Pain")

val itachi = NarutoCharacter("Itachi")
val kisame = NarutoCharacter("Kisame")
val itachiAndKisame = listOf(itachi.name, kisame.name)

val zouri = NarutoCharacter("Zouri")
val waraji = NarutoCharacter("Waraji")
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
