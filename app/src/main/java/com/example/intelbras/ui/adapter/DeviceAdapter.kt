//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.intelbras.databinding.ListItemBinding
//
//class DeviceAdapter: ListAdapter<AlarmCentral, DeviceAdapter.ViewHolder>(DIFF_CALBACK) {
//
//    companion object {
//
//        private val DIFF_CALBACK = object : DiffUtil.ItemCallback<AlarmCentral>() {
//            override fun areItemsTheSame(oldItem: AlarmCentral, newItem: AlarmCentral): Boolean {
//                return oldItem.name == newItem.name
//            }
//            override fun areContentsTheSame(oldItem: AlarmCentral, newItem: AlarmCentral): Boolean {
//                return oldItem.name == newItem.name
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            ListItemBinding.inflate(LayoutInflater.from(parent.context),
//            parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val AlarmCentral = getItem(position)
//
//        holder.binding.nameTextView.setText(AlarmCentral.name)
//
//    }
//
//    inner class ViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)
//
//}