fun main() {

    var inputUser = ""

    println("                Willkommen beim Spiel Naruto \n")
    Thread.sleep(3000)
    println("Möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' \n")
    inputUser = readln().lowercase()

    if (inputUser == "ja")
        rules()

    print("Möchtest du selbst wählen oder per Zufallsgenerator? \n Wähle 'selbst' oder 'Zufall'. : ")
    inputUser = readln().lowercase()
    if (inputUser == "selbst") {
        selectionCharacter()
    } else {
        // TODO Liste muss noch erstellt werden
    }
}

fun rules(){

    println("""
        Zuerst darfst du dir aussuchen ob du selbst deinen Charakter bestimmst oder per Zufall.
        Wenn du dir selbst deinen Charakter aussuchen möchtest wirst du gefragt ob du 
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

fun selectionCharacter(): String {

    var counter = 0
    var selection = ""

    while (counter < 5) {
        print("Wählst du ein Team oder einen einzelnen Charakter? \nWähle 'Team' oder 'Einzel'. : ")
        val inputTeamOrSingle = readln().lowercase()

        if (inputTeamOrSingle == "team") {
            print("Welches Team wählst du? : ")
            selection = "team"
            counter = 5
        } else if (inputTeamOrSingle == "einzel") {
            print("Welchen Charakter wählst du? : ")
            selection = "single"
            counter = 5
        } else {
            println("Du hast eine falsche Eingabe getroffen. Versuche es nochmal!")
            counter++
        }
    }
    return selection
}

fun selectionEnemy(): String {


}
