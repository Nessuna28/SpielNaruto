open class Character(
    var name: String,
    var attack: MutableMap<String, Int>,
    var ninjutsu: MutableMap<String, Int>,
    var weapon: MutableMap<String, Int>
) {

    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5

    // Lebenspunkte werden um einen bestimmten Wert verringert
    fun lostLifePoints(value: Int){

        lifePoints -= value
    }

    // Chakra wird um einen bestimmten Wert verringert
    fun lostChakra(value: Int){

        chakra -= value
    }

    // einfaches Ausweichen
    // der Spieler hat nur 5 Mal die Möglichkeit Baumstamm einzusetzen, nach jedem Mal wird einmal abgezogen
    open fun baumstamm(){

        baumstamm --
        println("Du bist ausgewichen!")
    }

    fun attackNormal(input: Int, enemy: Character): String {

        var attack = ""

            when (input){
                0 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(0)
                    attack = this.attack.keys.elementAt(0)
                }
                1 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(1)
                    attack = this.attack.keys.elementAt(1)
                }
                2 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(2)
                    attack = this.attack.keys.elementAt(2)
                }
                3 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(3)
                    attack = this.attack.keys.elementAt(3)
                }
            }
        return attack
    }

    fun attackWithNinjutsu(input: Int, enemy: Character): String {

        var attack = ""

        when (input){
            0 -> {
                enemy.lifePoints -= this.attack.values.elementAt(0)
                lostChakra(20)
                attack = this.attack.keys.elementAt(0)
            }
            1 -> {
                enemy.lifePoints -= this.attack.values.elementAt(1)
                lostChakra(20)
                attack = this.attack.keys.elementAt(1)
            }
            2 -> {
                enemy.lifePoints -= this.attack.values.elementAt(2)
                lostChakra(20)
                attack = this.attack.keys.elementAt(2)
            }
            3 -> {
                enemy.lifePoints -= this.attack.values.elementAt(3)
                lostChakra(20)
                attack = this.attack.keys.elementAt(3)
            }
            4 -> {
                enemy.lifePoints -= this.attack.values.elementAt(4)
                lostChakra(20)
                attack = this.attack.keys.elementAt(4)
            }
        }
        return attack
    }

    fun attackWithWeapon(input: Int, enemy: Character): String {

        var attack = ""

        when (input){
            0 -> {
                enemy.lifePoints -= this.attack.values.elementAt(0)
                attack = this.attack.keys.elementAt(0)
            }
            1 -> {
                enemy.lifePoints -= this.attack.values.elementAt(1)
                attack = this.attack.keys.elementAt(1)
            }
            2 -> {
                enemy.lifePoints -= this.attack.values.elementAt(2)
                attack = this.attack.keys.elementAt(2)
            }
            3 -> {
                enemy.lifePoints -= this.attack.values.elementAt(3)
                attack = this.attack.keys.elementAt(3)
            }
        }
        return attack
    }
}
