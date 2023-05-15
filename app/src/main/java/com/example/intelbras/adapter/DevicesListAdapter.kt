import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intelbras.R
import com.google.android.material.button.MaterialButton

class DeviceListAdapter : RecyclerView.Adapter<DeviceListAdapter.ViewHolder>() {

    private val deviceList = mutableListOf<String>()

    fun submitList(list: List<String>) {
        deviceList.clear()
        deviceList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deviceName = deviceList[position]
        holder.bind(deviceName)
    }

    override fun getItemCount() = deviceList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon)
        private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private val optionsButton: MaterialButton = itemView.findViewById(R.id.options_menu_button)

        fun bind(deviceName: String) {
            val icon = if (deviceName.startsWith("Ala")) {
                R.drawable.ic_alarm_item
            } else {
                R.drawable.ic_camera_icon
            }
            iconImageView.setImageResource(icon)
            nameTextView.text = deviceName

            optionsButton.setOnClickListener {
                // TODO: Implement options button functionality
            }
        }
    }
}
