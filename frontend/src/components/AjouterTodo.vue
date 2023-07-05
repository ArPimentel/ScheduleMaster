<template>
    <div class="form-todo-add">
        <h2>Ajouter un todo</h2>
        <form class="add-todo-form">
            <input type="text" v-model="newTodo.nom" placeholder="Titre" />
            <textarea type="text" v-model="newTodo.description" placeholder="Description"></textarea>
            <input type="date" v-model="newTodo.dateFin" placeholder="Date" />
            <select v-model="newTodo.idListe">
                <option v-for="liste in listes" :key="liste.id" :value="liste.id" selected>{{ liste.nom }}</option>
            </select>
            <div class="buttons">   
                <button class="cancel-button">Annuler</button>
                <button class="add-button" :disabled="!formValid" @click.prevent="addNewTodo">Ajouter</button>
            </div>
        </form>
    </div>
</template>

<script>

export default {
    name: 'AjouterTodo',
    data(){
        return {
            newTodo: {
                nom: '',
                description: '',
                dateFin: '',
                idListe: 0
                
            }
        }
    },
    props: {
        listes: {
            type: Array,
            required: true
        }
    },
    computed: {
        formValid() {
            console.log("prueba2")
            if(this.newTodo.nom !== '' && this.newTodo.description !== '' && this.newTodo.idListe !== 0) {
                return true;
            } else {
                return false;
            }
        }
    },
    methods: {
        addNewTodo() {
            console.log("ajouter un todo")
            this.$emit('add-new-todo', {
                ...this.newTodo
            })
        }


        
        
    }

}
</script>

<style>
.add-todo-form {
    display: flex;
    flex-direction: column;
    height: 400px;
    justify-content: space-around;
    padding: 10px 50px;
}

.add-todo-form input, textarea, select {
    padding: 10px;
    border: 1px solid grey;
}

.form-todo-add {
    background-color: white;
    border-radius: 8px;
}

.form-todo-add h2 {
    margin-left: 40px;
    padding: 10px;
    color: grey;
}

.form-todo-add .buttons{
    align-self: flex-end;
    gap: 10px;
    padding: 10px;
}

.add-button, .cancel-button {
    padding: 5px 20px;
    color: white;
    border-radius: 3px;
}

.add-button {
    background-color: #1a73e8;
    border: 1px solid #1a73e8;
}

.cancel-button {
    background-color: grey;
    border: 1px solid grey;
    margin-right: 10px;
}
</style>