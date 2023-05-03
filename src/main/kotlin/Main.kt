val white = "\u001b[0m"
val magenta = "\u001b[35m"
val blue = "\u001b[34m"

// diese beiden Variablen sind außerhalb der Main, sodass ich von überall etwas in ihnen abspeichern kann
// und nach Belieben aufrufen kann ohne ständig zwischendurch viele Variablen anlegen zu müssen
var inputUser = ""
var characterUser = ""
var characterComputer = ""

fun main() {

    greeting()

    selectionTeamOrCharacter()
    characterComputer()
    println(characterUser)




}

// der Spieler wird begrüßt und gefragt, ob er die Regeln hören möchte
// ist die Antwort ja werden ihm die Regeln angezeigt, andernfalls nicht
fun greeting() {

    println("$blue              Willkommen beim Spiel Naruto")
    println(
        """
       ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠑⠲⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠓⠦⣄⡀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⢀⠀⠀⠀⠰⡀⠀⠀⠀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠠⡀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠛⠁⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⣀⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⢳⣆⡀⠀⠙⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣻⡀⠀⠀⠀⠀⠀⣦⠀⠀⠀⠀⠀⣠⡴⠒⠉⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣦⣀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⣿⠀⠀⠀⠀⢀⡙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠺⠓⠊⠉⠉⣲⠀⠈⡇⠀⠀⠀⠀⠀⣿⣿⡉⠉⠉⠑⠦⣳⡄⠀⠀⠉⠉⠉⠉⠛⠻⠿⠿⣿⣿⣿⣿⡄⠀⠀⠀⣿⠀⠀⠀⠀⠀⢻⠲⠿⣆⡀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠏⠀⣴⣿⡄⠀⠀⠀⢀⡏⠛⠃⠀⠀⠀⠀⠀⠙⠀⣠⡤⣤⣀⣠⠦⠀⠀⠀⠀⠀⣍⠻⣿⣄⠀⠀⢻⠀⠀⠀⠀⠀⠀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠀⣼⣿⣿⣷⡀⠀⠀⢸⣇⠀⠀⠀⠀⠠⠄⠀⣰⡟⢉⣤⣤⡉⠁⠀⠀⠀⠀⠤⠀⠙⠁⢹⣿⣆⠀⢸⣧⡀⠀⠀⠀⠀⠸⡆⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⣴⣿⣿⣿⣿⣧⡀⠀⢘⡟⠁⠀⠀⠀⠀⢀⡾⢻⡄⠸⣤⣌⣿⠀⠀⠀⠀⡄⠀⠀⣤⠀⣿⣿⣿⣆⢸⣿⣿⣦⡀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⣾⡜⢹⣿⣿⣿⣿⣿⣷⡀⠐⣧⡀⠀⢀⣴⠀⠈⠙⠲⠿⠶⠴⠾⠋⠀⠀⠐⠺⠆⠀⠀⠈⠀⣿⣿⣿⣿⣾⣿⣿⣿⣟⢦⡀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠟⠁⢸⣿⣿⣿⣿⣿⣿⣷⡄⣟⣁⣀⣀⣠⣤⣤⣤⣤⣤⣄⣀⣀⣀⣀⣀⣀⠀⠀⠀⣴⡖⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢹⢦⡀⢸⡄⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⣸⠀⠙⢦⣧⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⣿⣿⡿⢻⡏⠉⠉⠈⠙⠲⢬⣗⡦⣄⠀⠀⠀⣠⠄⠀⠀⣹⠉⠉⢉⣟⣿⠟⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣆⣀⣠⣽⣦⣤⣤⣴⣶⣶
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡛⡄⠀⣸⠀⠀⣴⠞⢻⡽⢿⣾⣍⢾⣿⣦⠞⠁⠀⠀⠀⢿⣤⣶⡾⢛⣾⣿⣷⠶⣄⡀⠈⠉⢹⠙⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⣷⣿⠀⣿⠀⠀⢿⡄⢿⡴⢆⡷⠹⣎⢻⠅⠀⠀⠀⠀⢀⣴⣿⠏⣴⠏⣾⢰⠎⣳⢀⣿⠂⠀⢸⠀⠀⣾⣹⡟⢹⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡏⢱⣿⡇⡟⠀⠀⠈⠳⢤⣉⣉⣤⡤⠟⠀⠀⠀⠀⠀⠀⣼⡿⠁⠀⠻⣄⣉⣓⣚⣣⡾⠃⠀⠀⢸⠀⢸⣿⠻⠃⡟⢿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣇⣿⡿⢻⡇⠀⠀⠀⠀⣀⣀⡤⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢰⢿⣿⡀⣰⠃⠈⢻⣿⣷⡀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⢿⣷⡈⠃⣠⠤⠒⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⢠⡄⠰⢤⣀⠀⠀⠀⠀⢸⣿⣿⢟⣵⠏⠀⠀⠸⣿⣿⣷⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣦⠉⠙⣇⠀⠀⠀⠀⢀⣤⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢲⢤⡀⠀⠌⠙⠲⢤⡀⡼⠋⠁⣹⠋⠀⠀⠀⠀⢻⣿⣿⣄⡀⠀⣀⣀⣀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢦⠀⣿⠀⢀⡤⠞⠉⠀⠀⠀⠀⠀⠀⢀⣤⣀⢀⣀⣀⡀⠀⠀⠀⠈⠀⠈⠳⢆⡀⠀⠀⢙⣧⣀⡞⡇⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⡿⣿
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣈⣻⣹⣦⣉⣤⣤⣤⣾⣄⠀⠀⠀⠀⠈⠻⣽⣵⣟⡿⠃⠀⠀⠀⠀⠀⢧⡀⠀⠙⢦⣠⠟⢀⡿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡇⠀⠀⠀⠀⠀⠈⠛⠋⠀⠀⠀⠀⠀⠀⠀⠀⠙⢆⠀⢠⡟⠀⣸⡁⢸⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣦⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠲⠶⣶⣉⣉⣉⣉⣉⢉⣉⣭⠭⠟⠛⠀⠀⠀⠈⢳⡟⣶⠋⢸⣿⣿⣿⣿⣶⣶⣤⠀⠀⠀⠀⠀⠀⠈⠻⢿⡄
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡏⣹⣿⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⡿⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⢞⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡽⠁⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣧⡉⡳⢦⣀⣀⠀⠀⠀⠀⣀⣠⣴⣾⢟⣵⣿⣿⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠃⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠊⠀⠀⠈⠉⢟⡿⣻⣿⢿⣿⣽⡷⣿⣿⢞⡵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡎⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⢾⠂⠀⢀⢴⢵⡿⣺⢟⣷⡷⣻⣾⣾⢟⣵⠋⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⢀⣀⣠⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢄⣶⣿⣷⡊⢘⢼⡵⣫⣠⢾⣝⡷⡻⠃⠀⠘⣯⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀
    ⣀⣤⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠔⢹⠃⠈⠳⣞⡿⢻⠞⣵⣫⣫⡾⠁⠀⠀⠀⣸⡿⢙⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣽⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀
   
   $white""".trimIndent()
    )

    Thread.sleep(3000)
    print("Möchtest du dir die Regeln anzeigen lassen? \nWähle 'ja' oder 'nein' : ")
    inputUser = readln().lowercase()

    if (inputUser == "ja")
        rules()
}

// die Regeln des Spiels
fun rules() {

    println(
        """
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
        
    """.trimIndent()
    )
}

// der Spieler hat die Möglichkeit zu wählen, ob er mit einem Team spielen möchte oder mit einem einzelnen Charakter
// sofern die Eingabe nicht falsch ist, wird gleich die nächste Funktion je nach Eingabe ausgeführt
// bei falscher Eingabe hat der Spieler noch zwei Versuche
fun selectionTeamOrCharacter() {

    var counter = 0

    while (counter < 3) {
        print("\nWählst du ein Team oder einen einzelnen Charakter? \nWähle 'Team' oder 'Einzel'. : ")
        inputUser = readln().lowercase()

        if (inputUser == "team" || inputUser == "einzel") {
            selectionSelfOrRandom()
            break
        } else {
            println("Du hast eine falsche Eingabe getroffen. Versuche es nochmal!")
            counter++
        }
    }
}

// der Spieler hat die Möglichkeit selbst zu wählen oder per Zufallsgenerator
// je nach Eingabe wird die nächste Funktion ausgeführt
// bei falscher Eingabe hat der Spieler noch zwei Versuche
fun selectionSelfOrRandom() {

    var counter = 0
    val inputUserTeamOrCharacter = inputUser

    while (counter < 3) {
        print("\nMöchtest du selbst wählen oder per Zufallsgenerator? \n Wähle 'selbst' oder 'Zufall'. : ")
        inputUser = readln().lowercase()

        if (inputUser == "selbst") {
            println("\nDu hast dich entschieden selbst zu wählen. \n")
            if (inputUserTeamOrCharacter == "einzel") {
                selectionCharacter()
                break
            } else if (inputUserTeamOrCharacter == "team") {
                selectionTeam()
                break
            }
            counter = 4
        } else if (inputUser == "zufall") {
            println("\nDu hast dich für den Zufallsgenerator entschieden. \n")
            if (inputUserTeamOrCharacter == "einzel") {
                randomGeneratorForOneCharacter()
                break
            }else if (inputUserTeamOrCharacter == "team"){
                randomGeneratorForTeam()
                break
            }
            counter = 4
        } else {
            println("Du hast eine falsche Eingabe gemacht. Versuche es nochmal.")
            counter++
        }
    }
}

// dem Spieler werden die vorhandenen Charaktere angezeigt und er darf sich, per Eingabe, einen aussuchen
// Spieler hat wieder drei Versuche für die Eingabe
// bei drei falschen Eingaben, wird dem Spieler per Zufallsgenerator ein Spieler ausgesucht
fun selectionCharacter() {

    var counter = 0

    println(
        """
                
            Die Charaktere die du zur Auswahl hast sind: $blue
            ${characterList.slice(0..3)}
            ${characterList.slice(4..5)}
            
            $white
        """.trimIndent()
    )

    while (counter < 3) {
        print("Für welchen Charakter entscheidest du dich? Gib den Namen ein: ")
        inputUser = readln().lowercase()

        val lowercaseList = listToLowercaselist(characterList)

        if (lowercaseList.contains(inputUser)) {
            println("\nSuper! Du hast dich für $magenta${inputUser.uppercase()} ${white}entschieden.")
            characterUser = inputUser
            grafik(characterUser)
            break
        } else {
            println("\nDu hast eine falsche Auswahl getroffen.")
            counter++
        }
        println("Da du keine richtige Auswahl getroffen hast, wird dir ein zufälliger Charakter zugewiesen.")
        randomGeneratorForOneCharacter()
    }
}

// dem Spieler werden die vorhandenen Charaktere angezeigt und er darf sich, per Eingabe, drei Charaktere aussuchen
// auch hier wieder drei Versuche für die Eingabe
// bei drei falschen Eingaben, werden dem Spieler per Zufallsgenerator drei Spieler ausgesucht
fun selectionTeam(){

    var counter = 0

    println(
        """
                
            Die Charaktere die du zur Auswahl hast sind: $blue
            ${characterList.slice(0..3)}
            ${characterList.slice(4..5)}
            
            $white
        """.trimIndent()
    )

    while (counter < 3) {
        print("Für welche Charaktere entscheidest du dich? Gib drei Namen ein und trenne sie mit Komma: ")
        inputUser = readln().lowercase()
        val character1 = inputUser.split(", ")[0]
        val character2 = inputUser.split(", ")[1]
        val character3 = inputUser.split(", ")[2]

        val lowercaseList = listToLowercaselist(characterList)

        if (lowercaseList.contains(character1) && lowercaseList.contains(character2) && lowercaseList.contains(character3)) {
            println("\nSuper! Du hast dich für $magenta${inputUser.uppercase()} ${white}entschieden.")
            characterUser = inputUser
            break
        } else {
            println("\nDu hast eine falsche Auswahl getroffen.")
            counter++
        }
        println("Da du keine richtige Auswahl getroffen hast werden dir 3 zufällige Charaktere zugewiesen.")
        randomGeneratorForTeam()
    }
}

// der Zufallsgenerator für einen Charakter
fun randomGeneratorForOneCharacter() {

    val characterForRandom = characterList.random()

    Thread.sleep(2000)

    println("Dein Charakter ist $magenta${characterForRandom.uppercase()} $white")
    characterUser = characterForRandom
    grafik(characterUser)
}

// der Zufallsgenerator für drei Charaktere
// es kommt kein Charakter doppelt in einem Team vor
fun randomGeneratorForTeam(){

    val listOfCharactersForRandom = mutableListOf<String>()

    while (listOfCharactersForRandom.size < 3) {
        val randomCharacter = characterList.random()
        if (!listOfCharactersForRandom.contains(randomCharacter)) {
            listOfCharactersForRandom.add(randomCharacter)
        }
    }
    Thread.sleep(2000)
    println("Deine Charaktere sind: $magenta${listOfCharactersForRandom.toString().uppercase()} $white")
    characterUser = listOfCharactersForRandom.toString()
}

// die Auswahl für den Computer wird per Zufallsgenerator, je nachdem ob Team oder einzelner Charakter, getroffen
// es kommt kein Charakter doppelt in einem Team vor
fun characterComputer(){

    if ("," in characterUser){
        val listOfCharactersForRandom = mutableListOf<String>()

        while (listOfCharactersForRandom.size < 3) {
            val randomCharacter = characterList.random()
            if (!listOfCharactersForRandom.contains(randomCharacter)) {
                listOfCharactersForRandom.add(randomCharacter)
            }
        }
        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${listOfCharactersForRandom.toString().uppercase()} $white")
    } else {
        val selectionComputer = characterList.random()
        Thread.sleep(2000)
        println("\nDu trittst an gegen: $blue${selectionComputer.uppercase()} $white")
    }
}

// jeder Spieler hat seine eigene kleine Grafik
fun grafik(selectionPlayer: String){

    when (selectionPlayer) {
        "naruto" -> println(
            """
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⡀⠑⠒⠀⠠⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⣘⠖⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠴⡖⠒⠒⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠒⢄⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣄⣀⣀⣙⣦⡀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⣠⠚⢁⣀⡄⢰⠀⠀⢀⠀⠀⠀⣠⣤⣤⣤⣤⣶⣶⣶⣶⣶⣤⣤⣄⠀⠀⠀⠀⠀⠀⡀⠘⡄⠀⠀⠀⠁⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠚⠋⠉⠀⣘⣠⡇⠀⢠⣿⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⣠⡇⠀⢸⡟⠒⢼⡄⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⢀⣀⣀⣀⣀⣀⣴⣋⣸⠀⣴⣿⣿⠀⢠⠋⠉⠉⠉⠙⠉⠉⢉⡉⢀⡉⠁⠀⡃⢀⣴⣿⡇⢀⣿⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⠿⠛⠉⠙⠛⠿⢿⣧⢾⣿⣿⣿⢠⠧⠀⠀⡀⠀⠀⢠⡞⣩⣭⡋⠀⢀⢸⢠⠊⢼⣿⡇⣼⣿⡟⣲⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠿⠛⠉⠀⠀⠀⠀⠀⠀⢀⣼⡟⢾⠙⣿⣿⣾⣇⠄⠀⠀⠀⢰⣏⣣⣿⣿⡇⠀⠀⠟⠁⢀⣼⣿⣿⣿⠁⡿⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠿⡇⢸⣆⡇⠈⠙⠻⣷⣶⣤⣤⣤⣀⣀⣀⣀⣤⣤⣤⣶⣶⣿⠟⠋⠀⢸⢰⣧⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣠⡾⠟⠁⠀⢳⢷⣿⠃⠀⢰⡖⣿⣿⢯⣿⣟⠛⡟⠛⠻⡛⢛⣽⣿⣿⣿⡗⢦⠀⠘⣾⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⣀⣤⠞⠋⠀⠀⠀⠀⠈⣆⠛⡄⠀⠀⠣⡘⠿⢊⣘⠌⠙⠁⠀⠀⠹⠋⢸⡈⢻⣟⣡⠋⠀⠀⡏⡸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣶⡿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⣟⡶⢇⠀⠀⠀⠈⠉⠉⠉⠀⠚⠀⠀⠀⠀⠀⠀⠉⠉⠁⢀⠀⠀⢰⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡻⢁⠸⣀⣀⠶⠤⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠒⠒⠠⢤⡇⡇⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠟⢹⢀⣷⡀⢀⡀⠶⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠐⠢⠤⣀⡀⣼⣿⡃⠀⢀⣴⣶⣒⣉⣟⣿⠯⢖⡢⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⣿⣿⡀⠀⣀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢤⡀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣮⣅
        ⠀⠀⣀⣠⣤⣴⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣷⣎⠁⠀⠀⠀⠤⠤⢴⠒⠒⠂⠄⠀⠀⢨⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
        ⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⢀⣤⣿⠙⠆⠀⠀⢀⣴⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⡼⣱⠃⠀⣄⣠⣶⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣛⣿⠁⠇⠀⢀⣟⠻⠿⠿⠿⠿⠟⠛⢛⡛⠛⠛⡛⠛⠛⠛⠿⠿⠟⠛⠋⠉⠉
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⡀⠀⡎⡸⠀⠀⢸⠀⠀⠀⠀⢠⣀⠖⠖⠉⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠃⠀⠀⣿⣶⣶⣶⣶⠖⠁⠀⠀⡄⠐⠃⠀⠀⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⠎⠀⠀⣸⠿⠟⠛⠛⢣⠀⢀⣴⡟⠁⠀⢀⣀⡠⢤⣀⣀⠀⠀⠀⠀⠀⠀⠀
        ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⠎⠀⡀⣠⡿⠒⠢⡀⣠⠎⠀⢸⡏⠀⢀⠴⣯⣅⢀⢀⡉⣀⠀⣀⣨⣿⣿⣿⣿
        
    """.trimIndent()
        )

        "sasuke" -> println(
            """
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣠⣴⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⢻⣿⣿⣿⡿⣿⣿⣿⣿⠏⠀⣿⣿⠻⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⠁⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⢸⣿⣿⣿⠁⣿⣿⣿⠃⠀⢸⣿⠇⠀⢹⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⣀⠀⠀⠀⠀⣿⣿⠇⠀⣿⡿⠁⠀⠀⣼⠏⢀⣠⠴⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⠃⣿⠋⡀⢀⡉⢓⠦⢤⣼⣿⡴⠀⡟⠁⣀⣀⣴⠟⠛⣩⣤⡄⣸⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⡏⢰⠣⡞⢉⣿⣽⢿⠲⡐⢌⡇⠀⠀⠀⠀⡸⠁⣨⠖⣟⣩⣿⠉⢻⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀
        ⠀⠀⠀⠀⢀⣼⣿⡿⢛⣿⣿⣿⣿⣿⠇⠀⠀⠙⢦⣻⣿⣛⣤⣼⡀⠀⠀⠀⠀⢰⠁⠐⣧⣀⣻⣿⣋⡠⠋⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀
        ⠀⠀⠀⢀⣾⡿⠋⠀⢸⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⡀⠁⠉⠀⡀⢀⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀
        ⠀⠀⢀⡾⠋⠀⠀⠀⣸⣿⣿⣿⠳⣼⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠀⠀⢘⠷⡦⢀⡶⠇⢸⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
        ⠀⠀⠈⠀⠀⠀⠀⢠⣿⣿⣿⡏⠒⠚⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡤⠛⠉⠁⢀⣿⣿⣿⣿⣿⠙⢿⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠋⢸⣿⣿⣿⡄⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠁⠀⠀⡈⠉⠛⠿⠿⣇⠀⠈⠃⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡟⢸⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠓⢠⡇⠀⠀⠀⠀⠀⠉⠒⠤⡀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠁⢸⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⣒⡖⠊⡇⠀⠀⠀⠀⠀⠀⠀⠀⢨⠆⠀
        ⠀⠀⣆⡀⠀⠀⠀⠀⢸⡏⠀⣾⣿⠟⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠈⠁⠈⠉⠀⣠⠾⠁⢨⠇⠀⠀⠀⢀⠀⠀⠐⠀⡸⠀⠀
        ⠀⠀⢸⡉⠉⠉⠉⠉⠉⡇⢀⡿⠁⠀⣿⣿⣿⣿⠷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⢁⣼⠀⠀⠀⠀⠀⠀⠀⠀⡴⠁⠀⠀
        ⢆⠀⠀⢇⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⣿⠃⢹⣿⣤⠈⠓⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠔⢹⡏⠀⠀⠀⢠⡞⠀⠀⡜⠀⠀⠀⠀
        ⠈⢆⠀⠘⡄⠀⠠⡶⠿⢠⠂⠀⠀⢸⠃⠀⢸⣿⠈⠳⣄⠀⠈⠲⠤⣀⣀⣀⠀⣀⣀⠤⠚⠁⢀⣿⡇⠀⠀⢰⣋⠄⠀⡰⠁⠀⠀⠀⠀
        ⠀⠈⢆⠀⢱⠀⠀⠐⠊⠁⠀⢀⣠⠧⠤⢤⣼⠏⠀⠀⠈⠳⢤⡀⠀⠀⠈⠉⠋⠁⠀⠀⠀⢠⡾⢛⠃⠀⠠⡾⠃⠀⡰⠁⠀⠀⠀⠀⠀
        ⠀⠀⠀⠣⡀⢣⠀⠀⠀⠀⠀⡋⠁⠀⡴⠚⠁⠀⠀⠀⠀⠈⡄⠑⠢⢄⣀⣀⣀⣀⠤⠒⠋⠁⠀⢸⠀⠂⣒⡇⠀⡼⠑⡄⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠑⠄⢂⠀⠀⢀⠞⢁⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠘⠀⠈⠿⠀⡜⠁⠀⢸⡀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠈⣮⢦⣠⣷⠚⠁⠀⠀⠀⠀⢀⠀⠂⠀⠀⠀⠀⠈⢦⡀⠀⠀⠀⠀⠠⠀⠀⣀⠀⡇⠀⠀⢀⡜⠀⠀⠀⡸⠁⠈⠐⠂⢀
        
    """.trimIndent()
        )

        "sakura" -> println(
            """
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⢺⠱⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡜⠀⡇⢸⠀⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠁⠀⡇⢸⠀⠀⠈⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠁⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠁⠀⠀⣾⠾⡄⠀⠀⠀⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⢠⡇⠀⢠⠀⠀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠀⠘⣄⡇⠀⠀⣠⣄⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠸⡄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⠀⡼⠀⠀⢸⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⠀⠀⢻⣳⠀⠘⢿⣿⡷⠀⠘⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⢠⠇⠀⠀⠈⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠸⠄⠀⠀⢿⡄⠀⠀⠉⠀⠀⠀⠀⠈⠲⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⣧⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⣼⠀⠀⠀⡀⠀⠀⠀⠀⠀⢸⣅⠀⠀⠀⠀⠀⠀⠀⠘⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⢺⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠇⢠⠃⠀⠀⢠⠇⠀⠀⠀⠀⠀⣾⣬⣝⣦⡀⠀⠀⠸⣄⠀⠘⣇⠀⠀⡀⠀⢀⡞⠀⢀⣠⣴⣻⣟⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⢸⣆⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣠⡟⠀⠀⠀⢸⡆⠀⠀⠀⠀⠀⣿⠙⡏⠩⣿⠳⣦⣄⣀⡀⠀⣼⣆⠘⣇⣀⣈⣤⣶⡿⠛⠛⣠⡏⣿⠀⠀⠀⠀⠀⢰⠀⠀⠀⢰⣷⠘⣿⡀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡏⠀⠀⢀⠀⡀⠇⠀⠀⠀⠀⢸⠘⣦⣙⠶⠴⠞⠉⢻⣛⣯⡟⠁⠈⠀⢈⡿⢫⡾⣁⣙⣒⣒⣉⡴⢿⡇⠀⠀⠀⠀⡌⠀⠀⠀⠀⡏⡆⡇⣇⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⡴⡇⠀⡇⢰⠀⠀⠀⠀⡿⠀⠀⠈⠉⠉⠉⣩⠭⠗⠛⠿⠄⠀⠀⡾⠞⠉⠙⠲⠄⠀⠀⠀⠀⠈⡇⠀⠀⠀⢀⡇⠘⡇⠀⠀⡇⢳⡇⢸⡄⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠇⣠⠞⣿⢧⠀⡇⣸⠀⠀⠀⢈⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⡆⠀⠐⣿⠃⠀⢸⠀⠀⣷⠘⠇⠈⣇⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣧⠞⠁⢰⣻⡏⠀⣷⢿⠀⠀⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡇⡇⠀⢸⣸⠀⠀⢪⡇⠀⢻⠀⢠⡀⠸⡄
        ⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠁⠀⠀⢸⣿⡇⠀⡟⠸⡄⠀⠀⠀⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⡇⠃⠀⣿⣿⡧⠤⠜⠛⠲⠾⠦⣤⣧⡀⢳
        ⠀⠀⠀⠀⢀⡠⠒⠊⠉⠉⠉⠉⠉⠙⠛⠦⣤⣧⣀⡇⠀⡀⠀⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠲⣦⣄⠀⣠⣶⠂⠀⠀⠀⠀⠀⢀⡾⣿⣿⡇⠀⢀⣧⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉
        ⠀⠀⡀⠔⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⡇⠀⠸⠀⣿⣿⣿⣷⡄⠀⠀⠀⣀⠀⢀⣈⠽⠛⠛⠧⣄⣠⠖⠃⠀⣠⠏⣸⣿⣿⣇⠀⢸⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⡄⠀⡇⣿⣿⣿⣿⣟⢦⡀⠀⣠⠟⢋⣀⣀⣀⣀⣀⣈⣻⠇⣠⡾⢃⣼⣿⣿⣿⡿⠀⣷⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣇⠀⢧⢿⣿⣿⣿⣿⣧⣝⡟⢁⡴⠋⠀⠀⠀⠀⠀⠈⠙⣿⡫⣶⣿⣿⣿⣿⣿⡇⣼⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⡀⢸⣸⣿⣿⣿⣿⡟⠉⡴⠋⠀⠀⢀⣤⠤⢔⣶⠦⢤⣤⣴⣿⣿⣿⣿⣿⣿⢣⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣷⣸⣯⣿⣿⣿⣿⡵⠊⠀⠀⠀⣴⡿⠊⠉⠉⠙⠒⠛⢻⡛⢻⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠘⢷⣖⢤⡀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣀⣀⠴⠋⠁⠀⠀⢀⡠⠤⠤⠤⠤⠶⠾⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⢿⣧⣀⣀⣀⣀⣀⠤⠤⠶
        ⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣗⣲⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢀⣠⠾⠭⠤⠤⣄⣀⣈⣁⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠐⠒⠉⠁⠀⠀⠀⣀⡀⢀⣀⣛⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⢀⣤⣴⣾⠽⠛⠋⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⣀⣳⡀⠀⠀⠀⠀⣀⣠⣆⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⢠⡿⠛⠷⣄⣀⣠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠈⠉⠉⠉⠉⠉⠉⠐⠓⠛⠛⠛⠛⠛⠿⠿⠟⠛⠛⠛⠛⠛⠋⠉⠙⡆⠀⠀⠀⠀⠀⠀⠉⣷⢶⣯⡟⠀⣯⠉⠒⠦⣄⣀⣀⠀⠀⠀⢀⣀⣠⣶⣶⣶⣶⣤⣤⣤⣤⣄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⠀⠀⠀⢀⣗⡉⠀⢳⣤⣟⣇⣀⣀⣠⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠀⠀⠀⠀⢀⣼⣿⣯⢳⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⡭⣺⣿⣟⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⣿⣿⣯⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣀⣠⣤⣤⣤⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣷⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
        ⠀⠀⢀⠀⠀⠀⠀⠀⠠⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀
        ⠀
    """.trimIndent()
        )

        "shikamaru" -> println(
            """
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⡀⠀⠀⠀⠀⢠⡦⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡄⠀⣾⣿⣿⣿⣿⣇⠀⠀⢀⣴⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣾⣿⣿⣿⣿⣿⣿⣤⣴⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣦⡀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡤⠀⢠⡄⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣶⣤⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣶⡟⠁⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣤⣤⣤⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣁⣤⡾⠃⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⠿⠋⠁⣀⣀⣰⣦⣤⣤⣤⣟⣛⡻⢿⣿⣿⠟⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣿⣿⣥⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣧⣀⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⡟⢿⡈⠻⠌⠻⠀⠹⠀⠙⠙⢿⣿⣿⣿⡿⣿⡿⠟⣿⣿⣿⣿⣿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⡇⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡿⠋⠈⠉⠀⠐⠋⢡⠟⣿⣿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣸⣿⣿⣿⣿⠈⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢿⣿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡧⡘⢟⣿⡿⠝⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡿⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠷⢿⠀⢻⡇⠀⠀⠀⠓⠤⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣽⠃⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⣠⣿⠀⠈⠁⠀⠀⠀⡀⠀⠀⠉⠳⣄⡀⠀⠀⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣣⣽⡆⠀⠀⠀⠀⠀⢿⣽⠏⠑⠲⣌⡙⠶⠾⠁⠀⢿⣤⡤⣶⣶⠶⠖⠚⠁⣸⠁⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⢈⡉⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠀⠀⠀⠀⡜⠻⣟⣟⣉⣩⡿⠁⢰⠃⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠀⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠃⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣨⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣄⡀⢀⠀⠀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⣶⣶⣟⡯⠿⠟⢻⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠛⠁⠀⠀⠀⠀⢀⡼⠉⠀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⢿⣏⠁⠰⣀⠠⠒⢻⠀⢳⡄⠀⠀⠀⠓⠒⠒⠒⠒⠒⠒⠒⠒⠁⠀⣠⣞⡲⢄⡀⠀⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠈⢻⡇⠀⢹⡄⠀⡀⡇⠀⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣎⣡⠈⢻⣷⠙⣆⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⢸⡇⠀⠀⣿⣄⣦⠇⠀⠀⠙⢾⡽⠲⢤⣄⣀⣀⣀⣠⢴⣿⡭⠟⠁⣠⣾⠏⠀⣽⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⡼⡇⠀⢸⠓⣻⡿⠀⠀⠀⠀⠀⠙⢶⡬⢚⣓⠋⠙⠀⣸⡿⠁⠀⢸⣽⠁⠀⣸⣿⠀⠀⠀⠀
        ⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⣷⠀⠀⢸⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠙⠒⠿⠶⠶⢺⣿⡇⠀⠀⣎⡇⠀⢠⡗⡟⠀⠀⠀⠀
        ⠀⠀⠀⠀⢀⡠⠔⠛⠁⡇⠀⠀⠀⠀⡿⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠄⠀⠀⢹⠁⠀⢠⢿⠀⠠⠎⣴⡷⣄⠀⠀⠀
        ⣀⠀⠠⠒⠁⠀⠀⠀⠀⢸⠀⠀⠀⠀⠇⠂⠀⠸⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⢠⠎⠀⠀⠀⡼⠀⠀⡿⡟⠀⢐⢦⡟⠀⠀⠙⢦⣄
        
    """.trimIndent()
        )
    }
}

// eine Liste zu einer Liste mit nur Kleinbuchstaben umwandeln
fun listToLowercaselist(list: List<String>): List<String>{

    var lowercaseList = mutableListOf<String>()
    for (characters in list){
        lowercaseList.add(characters.lowercase())
    }
    return lowercaseList
}



