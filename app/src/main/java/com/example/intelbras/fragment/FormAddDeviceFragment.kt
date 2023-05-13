import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intelbras.MainActivity
import com.example.intelbras.databinding.FragmentAddProductsBinding

class FormAddDeviceFragment : Fragment() {


    private var _binding: FragmentAddProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            Toast.makeText(context, "Mensagem curta", Toast.LENGTH_SHORT).show()
        }
        binding.btnClose.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
