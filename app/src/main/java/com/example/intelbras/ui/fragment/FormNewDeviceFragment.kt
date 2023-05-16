import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intelbras.MainActivity
import com.example.intelbras.api.service.ServiceApi
import com.example.intelbras.databinding.FragmentFormAddDevicesBinding
import com.example.intelbras.dto.CreateAlarmCentralDto
import com.example.intelbras.dto.CreateVideoDeviceDto

import com.example.intelbras.network.ServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody

class FormNewDeviceFragment : Fragment() {

    private var _binding: FragmentFormAddDevicesBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner


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
        buttonClose()


        binding.fragmentHeader.btnSave.setOnClickListener {
            getValues()
        }

        spinner = binding.spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = parent?.getItemAtPosition(position).toString()

                if (selectedOption == "Vídeo") {
                    binding.fragmentForms.videoForm.visibility = View.VISIBLE
                    binding.fragmentForms.alarmForm.visibility = View.GONE
                    clear()
                } else if (selectedOption == "Alarme") {
                    binding.fragmentForms.videoForm.visibility = View.GONE
                    binding.fragmentForms.alarmForm.visibility = View.VISIBLE
                    clear()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun getValues() {
        val video = CreateVideoDeviceDto(
            name = binding.fragmentForms.editTextNameVideo.text.toString(),
            serial = binding.fragmentForms.editTextSerialVideo.text.toString(),
            username = binding.fragmentForms.editTextUserVideo.text.toString(),
            password = binding.fragmentForms.editTextPasswordVideo.text.toString()
        )

        val alarm = CreateAlarmCentralDto(
            name = binding.fragmentForms.editTextNameAlarm.text.toString(),
            macAddress = binding.fragmentForms.editTextAddressAlarm.text.toString(),
            password = binding.fragmentForms.editTextPasswordAlarm.text.toString()
        )

        if (alarm.name != "") {
            createAlarmCentrals(alarm)
        } else {
            createVideoDevice(video)
        }
    }

    fun createAlarmCentrals(alarm: CreateAlarmCentralDto) {
        val retrofit = ServiceProvider.getRetrofitInstance()
        val endpoint = retrofit.create(ServiceApi::class.java)
        val request = endpoint.createAlarmeCentral(alarm)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    Toast.makeText(context, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                    clear()
                } else if (response.code() == 409) {
                    Toast.makeText(context, "Equipamento já cadastrado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        context,
                        "Falha ao cadastrar Central de Alarme",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }
        })
    }

    fun createVideoDevice(video: CreateVideoDeviceDto) {
        val retrofit = ServiceProvider.getRetrofitInstance()
        val endpoint = retrofit.create(ServiceApi::class.java)
        val request = endpoint.createVideoDevices(video)
        request.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    Toast.makeText(context, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                    clear()
                } else if (response.code() == 409) {
                    Toast.makeText(context, "Equipamento já cadastrado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        context,
                        "Falha ao cadastrar Camera",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }
        })
    }

    private fun clear() {
        binding.fragmentForms.editTextNameVideo.setText("")
        binding.fragmentForms.editTextSerialVideo.setText("")
        binding.fragmentForms.editTextUserVideo.setText("")
        binding.fragmentForms.editTextPasswordVideo.setText("")
        binding.fragmentForms.editTextNameAlarm.setText("")
        binding.fragmentForms.editTextAddressAlarm.setText("")
        binding.fragmentForms.editTextPasswordAlarm.setText("")
    }

    private fun buttonClose() {
        binding.fragmentHeader.btnClose.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
