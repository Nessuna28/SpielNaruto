fun fight() {}

// diese Funktion fragt den Spieler, ob er angreifen oder ausweichen möchte und ruft je nach Antwort die dazugehörigen Funktionen auf
fun selectionAttackUser() {

    if (characterUser.name.isNotEmpty()) {
        print("\nMöchtest du (1) angreifen oder (2) ausweichen? Gib die jeweilige Zahl ein: ")
        selectionUserInt = readln().toInt()
        if (selectionUserInt == 1) {
            if (characterUser is CharacterWithGenjutsu) {
               characterUser.showSelection()
                characterUser.lostLifePoints(selectionUserString, characterComputer)

            } else if (characterUser is CharacterWithMedicalSkills) {
                characterUser.showSelection()
                characterUser.lostLifePoints(selectionUserString, characterComputer)
            } else {
                characterUser.showSelection()
                characterUser.lostLifePoints(selectionUserString, characterComputer)
            }
        } else if (selectionUserInt == 2) {
            characterUser.baumstamm("user")
            printForDodging("user")
            characterUser.lostLifePoints(selectionUserString, characterComputer)
        }
    }
}

// diese Funktion wählt ein Angriff oder Verteidigung per Zufall aus je nachdem welchen Charakter der Computer hat
// und speichert die Auswahl dan in der Variablen selectionComputer in der Main
fun attackComputer(){

    val attackList = mutableListOf<String>()

    if (characterComputer is CharacterWithGenjutsu) {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Genjutsu")
    } else if (characterComputer is CharacterWithMedicalSkills) {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Heilung")
    } else {

        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
    }

    selectionComputer = attackList.random()

    if (selectionComputer in characterComputer.attack.keys){
        characterComputer.lostLifePoints(selectionComputer, characterUser)
    } else if (selectionComputer in characterComputer.ninjutsu.keys){
        characterComputer.attackWithNinjutsu(selectionComputer, 0)
        characterComputer.lostLifePoints(selectionComputer, characterUser)
    } else if (selectionComputer in characterComputer.weapon.keys) {
        characterComputer.lostLifePoints(selectionComputer, characterUser)
    } else if (selectionComputer == "Genjutsu") {
        (characterComputer as CharacterWithGenjutsu).attackWithGenjutsu(characterUser)
    } else if (selectionComputer == "Heilung") {
        (characterComputer as CharacterWithMedicalSkills).heal(selectionComputer)
    } else if (selectionComputer == "Baumstamm") {
        characterComputer.baumstamm("com")
        printForDodging("com")
    }
}

fun printValueOfCharacter(){

    println("Spieler 1: $magenta$nameUser ${characterUser.name} ${characterUser.lifePoints} ${characterUser.baumstamm}")
    println("Spieler 2: ${blue}Computer ${characterComputer.name} ${characterComputer.lifePoints} ${characterComputer.baumstamm}")
}

fun printForDodging(player: String){

        if (selectionComputer != "Baumstamm") {
            if (player == "user" && selectionUserString != "Baumstamm"){
                println("\nDer Computer wollte mit $selectionComputer angreifen aber du bist ausgewichen.\nEr hat dich nicht getroffen.")
        } else {
            println("\nDer Computer ist ebenfalls ausgewichen")
        }
    } else {
        println("\nDu wolltest mit $selectionUserString angreifen aber der Computer ist ausgewichen.")
    }

    fun grafikForAttack(){

        println("""
                             |               !!!            o                     
     (((         |.===.       `  _ _  '      ` /_\ '        ()_()     
    (o o)        {}o o{}     -  (OXO)  -    - (o o) -       (o o)     
ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--`o'--Ooo-
 #   ___      
 #  <_*_>     
 #  (o o)     
-8---(_)--Ooo-


🌀 🔥 💣 
        """.trimIndent())
    }
}