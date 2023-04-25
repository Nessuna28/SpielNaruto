fun main() {

    var inputUser = ""

    println("                Willkommen beim Spiel Naruto \n")
    Thread.sleep(3000)
    print("Möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' : ")
    inputUser = readln().lowercase()

    if (inputUser == "ja")
        rules()

    var selectionPlayer = ""

   selectionSelfOrRandom(selectionPlayer)

    val selectionComputer = (teamList + characterList).random()
    println("Du trittst an gegen $selectionComputer")
    println(selectionPlayer)


}

fun rules(){

    println("""
        Zuerst darfst du dir aussuchen ob du selbst deinen Charakter bestimmst oder per Zufall.
        Wenn du dir selbst deinen Charakter aussuchen möchtest wirst du gefragt ob du dir ein Team oder einen einzelnen Charakter 
        aussuchen möchtest.
        Dann darfst du noch entscheiden ob du dir deinen Gegner aussuchen möchtest oder ob du einen zufälligen Gegner bekommst.
        Wenn du deine Auswahl getroffen hast wird gekämpft.
        Je nach dem für was du dich entschieden hast beeinflusst es die Anzahl der Züge pro Runde. Hast du dich für ein Team entschieden
        greift jedes Teammitglied in einer Runde an, hast du nur einen einzelnen Charakter greifst du auch nur einmal an.
        Dieses gilt natürlich auch für den Gegner (in dem Fall der Computer).
        Jeder ist eine Runde dran und darf angreifen, entweder bekommt der Gegner so viel Lebenspunkte abgezogen wie
        die Attacke Schaden verursacht oder er hat den Angriff abgewehrt.
        Wer zuerst keine Lebenspunkte mehr hat verliert dieses Spiel.
    """.trimIndent())
}

fun selectionSelfOrRandom(selectionPlayer: String){

    var selectionPlayer = selectionPlayer
    var inputUser = ""

    print("Möchtest du selbst wählen oder per Zufallsgenerator? \n Wähle 'selbst' oder 'Zufall'. : ")
    inputUser = readln().lowercase()

    if (inputUser == "selbst") {
        selectionTeamOrCharacter()
        if (selectionTeamOrCharacter() == "single"){
            selectionCharacter(selectionPlayer)
        } else if (selectionTeamOrCharacter() == "team"){
            selectionTeam(selectionPlayer)
        }
    } else if (inputUser == "zufall") {
       // selectionPlayer = randomGenerator().toString()
    } else {
        println("Du hast eine falsche Eingabe gemacht. Versuche es nochmal.")
    }
}

fun selectionTeamOrCharacter(): String {

    var selection = ""

    println("Du hast dich entschieden selbst zu wählen. \n")

        print("Wählst du ein Team oder einen einzelnen Charakter? \nWähle 'Team' oder 'Einzel'. : ")
        val inputUser = readln().lowercase()

        if (inputUser == "team") {
            selection = "team"
        } else if (inputUser == "einzel") {
            selection = "single"
        } else {
            println("Du hast eine falsche Eingabe getroffen. Versuche es nochmal!")
        }
    return selection
}

fun selectionCharacter(selction: String){

    var selection = selction

        println(
            """
            Die Charaktere die du zur Auswahl hast sind:
            $characterList
        """.trimIndent()
        )

            print("Für welchen Charakter entscheidest du dich? Gib den Namen ein: ")
            val inputUser = readln().lowercase()
            for (character in characterList) {
                if (character.lowercase() == inputUser) {
                    println("Super! Du hast dich für ${inputUser.uppercase()} entschieden.")
                    selection = inputUser
                    break
                }
            }
}

fun selectionTeam(selction: String){

    var selection = selction

        println(
            """
            Die Teams die du zur Auswahl hast sind:
            $teamList
        """.trimIndent()
        )

        print("Für welches Team entscheidest du dich? Gib den Namen des Teamleiters ein: ")
        val inputUser = readln().lowercase()
        for (team in teamList) {
            if (team.toString() == inputUser) {
                println("Super! Du hast dich für Team ${inputUser.uppercase()} entschieden.")
                selection = inputUser
                break
            }
        }
}

/*fun randomGenerator(): Any {

        val selectionFromRandomGenerator = (teamList + characterList).random()

        println("Du hast dich für den Zufallsgenerator entschieden. \n")

        if (selectionFromRandomGenerator == teamList) {
            println("Dein Team ist $selectionFromRandomGenerator")
        } else {
            println("Dein Charakter ist $selectionFromRandomGenerator")
        }
        return selectionFromRandomGenerator
}*/


