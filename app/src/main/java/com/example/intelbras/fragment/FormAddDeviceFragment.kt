import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intelbras.MainActivity
import com.example.intelbras.R
import com.example.intelbras.databinding.FragmentFormAddDevicesBinding

class FormAddDeviceFragment : Fragment() {


    private var _binding: FragmentFormAddDevicesBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner
    private lateinit var frame: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormAddDevicesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentHeader.btnSave.setOnClickListener {
            Toast.makeText(context, "Mensagem curta", Toast.LENGTH_SHORT).show()
        }
        binding.fragmentHeader.btnClose.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        spinner = binding.spinner
        frame = binding.formContainer

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = parent?.getItemAtPosition(position).toString()
                showForm(selectedOption, frame)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // faz nada
            }
        }
        spinner.setSelection(0)

    }

    private fun showForm(selectedOption: String, container: FrameLayout) {
        val layoutResId = when (selectedOption) {
            "Vídeo" -> R.layout.fragment_video_form
            "Alarme" -> R.layout.fragment_alarm_form
            else -> throw IllegalArgumentException("Opção inválida")
        }

        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(layoutResId, container, false)
        container.removeAllViews()
        container.addView(view)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
