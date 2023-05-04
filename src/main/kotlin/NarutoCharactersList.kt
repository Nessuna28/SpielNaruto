// Liste der Charaktere

val naruto = Character("Naruto", mutableMapOf("Taijutsu" to 10), mutableMapOf("sexy Jutsu" to 15, "Rasengan" to 45, "Schattendoppelgänger" to 30), mutableMapOf("Kunai" to 15))
val sasuke = CharacterWithGenjutsu("Sasuke", mutableMapOf("Taijutsu" to 10), mutableMapOf("Phönixblume" to 40, "Jutsu des flammenden Feuerballs" to 30, "Chidori" to 45), mutableMapOf("Kunai" to 15), 60)
val sakura = CharacterWithMedicalSkills("Sakura", mutableMapOf("Taijutsu" to 10), mutableMapOf("Kirschblütenschlag" to 30, "große Sakura" to 15, "Kirschblütenformation" to 45), mutableMapOf("Kunai" to 15), true)

val shikamaru = Character("Shikamaru", mutableMapOf("Taijutsu" to 10), mutableMapOf("Schattenfessel" to 50, "Schattenwürg" to 35, "Strategie" to 30), mutableMapOf("Kunai" to 15))
val choji = CharacterWithMoreStrength("Choji", mutableMapOf("Taijutsu" to 10), mutableMapOf("Fleischbombenpanzer" to 35, "Schmetterlingsflügel" to 45, "Jutsu der Teilentfaltung" to 30), mutableMapOf("Kunai" to 15), 15)
val ino = CharacterWithMedicalSkills("Ino", mutableMapOf("Taijutsu" to 10), mutableMapOf("Gedankenkontroll-Jutsu" to 50, "Schlafbomben-Jutsu" to 20), mutableMapOf("Kunai werfen" to 15), true)

var characterList = listOf(naruto, sasuke, sakura, shikamaru, choji, ino)
/*
val teamKakashi = listOf(naruto.name, sasuke.name, sakura.name)
val teamAsuma = listOf(shikamaru.name, choji.name, ino.name)

// Team Kurenai
val hinata = Characters("Hinata")
val kiba = Characters("Kiba")
val shino = Characters("Shino")

val teamKurenai = listOf(hinata.name, kiba.name, shino.name)

// Team Mighty Guy
val rockLee = Characters("Rock Lee")
val neji = Characters("Neji")
val tenten = Characters("Tenten")

val teamGuy = listOf(rockLee.name, neji.name, tenten.name)

// Team aus Sunagakure
val gaara = Characters("Gaara")
val kankuro = Characters("Kankuro")
val temari = Characters("Temari")

val teamSunagakure = listOf(gaara.name, kankuro.name, temari.name)

// Einzelkämpfer
val kakashi = Characters("Kakashi")
val asuma = Characters("Asuma")
val kurenai = Characters("Kurenai")
val mightyGuy = Characters("Mighty Guy")
val minato = Characters("Minato")
val tsunade = Characters("Tsunade")
val jiraiya = Characters("Jiraiya")

// böse Charakter
val orochimaru = Characters("Orochimaru")
val zabuza = Characters("Zabuza")
val haku = Characters("Haku")
val kabuto = Characters("Kabuto")
val deidara = Characters("Deidara")
val pain = Characters("Pain")

val itachi = Characters("Itachi")
val kisame = Characters("Kisame")
val itachiAndKisame = listOf(itachi.name, kisame.name)

val zouri = Characters("Zouri")
val waraji = Characters("Waraji")
val zouriAndWaraji = listOf(zouri.name, waraji.name)


 */
val characterNameList = listOf(
    naruto.name, sasuke.name, sakura.name,
    shikamaru.name, choji.name, ino.name
    )


fun main() {

}

