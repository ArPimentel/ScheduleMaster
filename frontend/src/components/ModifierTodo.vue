<template>
    <div class="form-todo-update">
  <form class="update-todo-form">
        <label name="title">Votre titre</label>
        <input type="text" name="title" v-model="updatedTodo.nom" placeholder="Titre" />
        <textarea type="text" v-model="updatedTodo.description"></textarea>
        <input type="date" v-model="updatedTodo.dateFin" />
        <select v-model="updatedTodo.liste_id">
            <option v-for="liste in listes" :key="liste.id" :value="liste.id" :selected="liste_id == liste.id">{{ liste.nom }}</option>
        </select>
        <div class="buttons">   
            <button class="cancel-button" @click.prevent="cancelModif">Annuler</button>
            <button class="update-button" :disabled="!formValid" @click.prevent="updateTodo">Mettre à jour</button>
        </div>
    </form>
</div>
</template>

<script>
export default {
    name: 'ModifierTodo',
    data() {
        console.log("data")
        return {
            updatedTodo: {
                nom: this.nom,
                description: this.description,
                dateFin: this.dateFin,
                liste_id: this.liste_id
            }
        }
    },
    props: {
        nom: String,
        description: String,
        dateFin: Date,
        listes: Array,
        liste_id: Number
    },
    computed: {
        formValid() {
            console.log("PRUEBA DE FORMULAIRE")
            if(this.updatedTodo.nom !== '' && this.updatedTodo.description !== '' && this.updatedTodo.liste_id !== 0) {
                return true;
            } else {
                return false;
            }
        }
    },
    methods: {
        updateTodo() {
            console.log("HOLA")
            this.$emit('update-todo', {
                ...this.updatedTodo
            });
            
        },
        changeDisplayState() {
        this.displayFlex = !this.displayFlex
        },
        cancelModif(){
            this.$emit('cancel-modif');
        }
    }

}
</script>

<style>

.form-todo-update{
    background-color: white;
    border-radius: 8px;

}
.update-todo-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 10px;

}
.update-button, .cancel-button {
    padding: 5px 20px;
    color: white;
    border-radius: 3px;
}
.update-button {
    background-color: blueviolet;
    border: 1px solid blueviolet;
}

.cancel-button {
    background-color: grey;
    border: 1px solid grey;
    margin-right: 10px;
}
.update-todo-form input, textarea, select {
    border-radius: 5px;
    padding: 10px;
    border: 1px solid grey;
    width: 200px;
    opacity: 0.6;
    
}

</style>