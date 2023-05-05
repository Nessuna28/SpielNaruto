class CharacterWithMedicalSkills: Character {

    var medicalSkills: Int

    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, medicalSkills: Int):
            super(name, attack, ninjutsu, weapon){

        this.medicalSkills = medicalSkills
    }

    // diese Funktion ermöglicht es dem Spieler sich zu heilen in dem seinen lifePoints den Wert des Skills dazu gerechnet werden
    // aber mehr als 500 (Anfangswert) gehen nicht
    // diese Fähigkeit verbraucht Chakra um den Wert des Skills
    fun heal(input: String) {

        if (input == selectionUserString) {
            if (this.chakra >= medicalSkills) {
                if (lifePoints < lifePointStart) {
                    this.lifePoints += medicalSkills
                    lostChakra(medicalSkills)
                    if (lifePoints > lifePointStart) {
                        lifePoints = lifePointStart
                    }
                    coloredBar()
                    println("\nDu wurdest geheilt!")
                } else if (this.lifePoints == lifePointStart) {
                    println("\nDeine Lebenspunkte sind voll. Diese Auswahl war unnötig.")
                }
                selectionUserString = "Heilung"
            } else {
                println("\nDu hast nicht genügend Chakra um dich zu heilen. Wähle erneut!")
                showSelection()
            }
        } else if (input == selectionComputer) {
            if (this.chakra >= medicalSkills) {
                if (lifePoints < lifePointStart) {
                    this.lifePoints += medicalSkills
                    lostChakra(medicalSkills)
                    if (lifePoints > lifePointStart) {
                        lifePoints = lifePointStart
                    }
                    coloredBar()
                    println("\nDer Computer wurde geheilt!")
                }
            } else {
                attackComputer()
            }
        }
    }

    // in der Funktion wird ein farbiger Balken generiert für die Funktion heal
    fun coloredBar(){

        var coloredBar = StringBuilder()
        coloredBar.append("|")

        val life = lifePoints / 10
        val to = life + 10

        for (point in 0..life){
            coloredBar.append("$blueBackground |")
        }
        Thread.sleep(2000)

        for (point in 0..to){
            print("\r$greenBackground$coloredBar$white")
            coloredBar.append(" |")
            Thread.sleep(800)
        }
    }

    // die Funktion aus Character um die Möglichkeit der Heilung erweitert
    override fun showSelection() {

        var counter = 0

        do {
            println(
                """
                    
            Womit möchtest du angreifen?
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Heilung
        """.trimIndent()
            )
            print("Gib die jeweilige Zahl ein: ")
            selectionUserInt = readln().toInt()

            if (selectionUserInt == 1) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in attack.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                characterUser.attackNormal(selectionUserInt)
                counter = selectionUserInt
            } else if (selectionUserInt == 2) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in ninjutsu.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                characterUser.attackWithNinjutsu(selectionUserString, selectionUserInt)
                counter = selectionUserInt
            } else if (selectionUserInt == 3) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in weapon.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                characterUser.attackWithWeapon(selectionUserInt)
                counter = selectionUserInt
            } else if (selectionUserInt == 4){
                println("Möchtest du Heilung anwenden?")
                print("Gib ein ja oder nein: ")
                selectionUserString = readln().lowercase()
                if (selectionUserString == "ja"){
                    heal(selectionUserString)
                    counter = selectionUserInt
                } else {
                    continue
                }
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != selectionUserInt)
    }


}