package com.example.trainmvvp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRvAdapter(private val context: Context,private val listener:INotesRvAdapter):RecyclerView.Adapter<NotesRvAdapter.NoteViewHolder>() {
    val allNotes=ArrayList<Note>()
    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<Button>(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{(allNotes[viewHolder.adapterPosition])}
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote=allNotes[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
}
interface INotesRvAdapter{
    fun onItemClicked(note: Note)
}