import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.exampe.travel.Journey
import com.example.travel.R

class JourneyAdapter(context: Context, private val journeys: List<Journey>) : ArrayAdapter<Journey>(context, 0, journeys) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val journey = journeys[position]
        val nameTextView = view.findViewById<TextView>(R.id.item_name)
        val descriptionTextView = view.findViewById<TextView>(R.id.item_description)

        nameTextView.text = journey.location
        val description = "${journey.startDate} - ${journey.endDate}"
        descriptionTextView.text = description

        return view
    }
}
