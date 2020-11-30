package com.platzi.conf.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.platzi.conf.R
import com.platzi.conf.model.Conferences
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.text.View
import javax.xml.soap.Text
import kotlin.collections.ArrayList

class ScheduleAdapter (val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    var listConference = ArrayList<List<Conferences>>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, attachToRoot false))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conferences
        holder.tvConferenceName.text=conference.title
        holder.tvConferenceSpeaker.text=conference.speaker
        holder.tvConferenceTag.text=conference.tag

        val simpleDateformat = SimpleDateFormat(pattern: "HH:MM")
        val simpleDateformatAMPM= SimpleDateFormat(pattern: "a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = SimpleDateFormat.format(conference.datetime)

        holder.tvConferenceHour.text=hourFormat
        holder.tvConferenceAMPM.text=simpleDateformatAMPM.format(conference.datetime).toUpperCase()
    }

    fun updateData(data: List<Conferences>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }
}