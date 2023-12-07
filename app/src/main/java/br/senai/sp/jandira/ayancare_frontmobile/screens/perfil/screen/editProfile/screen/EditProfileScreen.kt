package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.screen

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.DoencaResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository.CadastroRepository
import br.senai.sp.jandira.ayancare_frontmobile.screens.MaskVisualTransformation
import br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarContato.components.NumberDefaults
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.components.DropdownGender
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfileEdit
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.DateEditProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.ModalAddChronicDiseases
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.ModalAddComorbidity
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.funcaoQueChamaSqlLite.deleteUserSQLite
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.funcaoQueChamaSqlLite.saveLogin
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope,

) {

    val context = LocalContext.current
    var isDialogVisibleChronicDiseases by remember { mutableStateOf(false) }

    var isDialogVisibleComorbidity by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current

    var selectedDate by remember { mutableStateOf("") }
    var selectedDrop by remember { mutableStateOf("") }

    var validateDate by rememberSaveable {
        mutableStateOf(true)
    }










    val validateDateError = "Data está em branco"

    val scrollState = rememberScrollState()

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()
    var foto = paciente.foto
    var email = paciente.email
    var token = paciente.token
    var genero = paciente.genero
    selectedDrop = genero

    //FireBase
    val isUploading = remember { mutableStateOf(false) }
    val img: Bitmap =
        BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.alert_dark_frame)
    val bitmap = remember {
        mutableStateOf(img)
    }

    var imagemState by remember {
        mutableStateOf<Uri?>(null)
    }


    val launcherImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)

        } else {
            val source = it?.let { it1 ->
                ImageDecoder.createSource(context.contentResolver, it1)
            }
            bitmap.value = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }!!
        }

        imagemState = it

    }





    var nomeState by remember {
        mutableStateOf("")
    }
    var cpfState by remember {
        mutableStateOf("")
    }
    var cpfState2 by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }





    //Obter foto da galeria de imagens
    //variavel que vai guardar a uri
    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ){
//        photoUri = it
//    }

    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(photoUri).build()
    )

    // Mantenha uma lista de  patients no estado da tela
    var listPaciente by remember {
        mutableStateOf(
            Paciente(
                0, "", "", "", "", "", "", "",
                emptyList(),
                emptyList()
            )
        )
    }

    var call = RetrofitFactory.getPatient().getPatientById(id = id.toString())


    call.enqueue(object : Callback<PacienteResponse> {
        override fun onResponse(
            call: Call<PacienteResponse>,
            response: Response<PacienteResponse>
        ) {
            listPaciente = response.body()!!.paciente
            Log.e("TAG", "onResponse: $listPaciente", )

            selectedDate = listPaciente.data_nascimento

            nomeState = listPaciente.nome
            cpfState = listPaciente.cpf
            cpfState2 = listPaciente.cpf
            selectedDate = listPaciente.data_nascimento
            senha = listPaciente.senha
            //imagemState = listPaciente.foto

            Log.i("chegueiii", "onResponse: ${listPaciente.foto}")

        }
        override fun onFailure(call: Call<PacienteResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    fun atualizarDados(
        token: String,
        id: Int,
        nome: String,
        data_nascimento: String,
        email: String,
        senha: String,
        foto: String,
        cpf: String,
        id_endereco_paciente: Int,
        genero: String
    ) {
        val userRepository = CadastroRepository()
        lifecycleScope.launch {

            val response = userRepository.updateUser(
                token,
                id,
                nome,
                data_nascimento,
                email,
                senha,
                foto,
                cpf,
                id_endereco_paciente,
                genero
            )

            Log.e("response", "finalizarCadastro: $response")

            if (response.isSuccessful) {
                deleteUserSQLite(context, id)

                saveLogin(
                    context = context,
                    id = id.toLong(),
                    nome = nome,
                    token = token!!,
                    email = email,
                    foto = foto,
                    dataNascimento = selectedDate,
                    genero = selectedDrop,
                    tipo = "Paciente"
                )

                Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")

                navRotasController.navigate("main_screen")

            } else {

                val errorBody = response.errorBody()?.string()
                Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
                Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()

            }
        }
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()
            IconButton(
                onClick = {
                    navRotasController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center),
                            shape = CircleShape,
                            border = BorderStroke(
                                width = 2.dp,
                                Brush.horizontalGradient(
                                    listOf(
                                        colorResource(id = R.color.purple_200),
                                        Color.White
                                    )
                                )
                            )
                        ) {
//                            Image(
//                                painter = painter,
//                                contentDescription = "imagem do usuário",
//                                //colorFilter = ColorFilter.tint(colorResource(id = R.color.pink_login)),
//                                modifier = Modifier.fillMaxSize(),
//                                contentScale = ContentScale.Crop
//                            )

                            AsyncImage(
                                model = if(imagemState != null){
                                            imagemState
                                            }else{
                                                 listPaciente.foto
                                                 },
                                contentDescription = "",
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(
                                        BorderStroke(4.dp, Color.White),
                                        CircleShape
                                    )
                                    .padding(4.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Image(
                            painter = painterResource(
                                id = R.drawable.baseline_camera_alt_24
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .offset(x = 3.dp, y = 3.dp)
                                .size(30.dp)
                                .clickable {

                                    launcherImage.launch("image/*")
                                },
                        )
                    }

//                    DefaultTextField(
//                        valor = nome,
//                        label = "Nome Completo",
//                        onValueChange = { nome = it},
//                        aoMudar = {}
//                    )
                    OutlinedTextField(
                        value = nomeState,
                        onValueChange = {
                            //nomeState = it
                            if (it.length <= 50) {
                                nomeState = it
                            }
                                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        label = {
                            Text(
                                text = "Nome Completo"
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
//                        TextFieldCpf(
//                            cpfState = cpfState,
//                            aoMudar = {
//                                      cpfState = it
//                            },
//                            placeholder = "CPF",
//                            isError = false
//                        )
//                        OutlinedTextField(
//                            value = cpfState,
//                            onValueChange = {
//                                //quantidadeState = it
//                                if (it.length <= 11) {
//                                    cpfState = it
//                                }
//                            },
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            keyboardOptions = KeyboardOptions.Default.copy(
//                                keyboardType = KeyboardType.Number
//                            )
//                        )
                        OutlinedTextField(
                            value = cpfState2,
                            onValueChange = {
                                //quantidadeState = it
                                if (it.length <= 11) {
                                    cpfState2 = it
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            visualTransformation = MaskVisualTransformation(NumberDefaults.MASKCPF)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Log.i("data chegando", "EditProfileScreen: $selectedDate")
                    DateEditProfile(
                        context = context,
                        selectedDate = selectedDate,
                        onDateChange = {
                            selectedDate = it
                        },
                        focusManager = focusManager,
                        datePickerState = datePickerState,
                        validateDate = validateDate,
                        validateDateError = validateDateError,
                        label = "$selectedDate"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DropdownGender(
                        context = context,
                        gender = selectedDrop,
                        onValueChange = { selectedDrop = it }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Doenças Crôninas",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = { isDialogVisibleChronicDiseases = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "",
                            tint = Color(0xFF35225F),
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
                if (isDialogVisibleChronicDiseases) {
                    ModalAddChronicDiseases(
                        isDialogVisibleChronicDiseases = false,
                        navController = navController,
                        lifecycleScope = lifecycleScope,
                        nav = "edit_profile_screen"
                    )
                }
                LazyRow{
                    items(listPaciente.doencas_cronicas.reversed()) {

                        var text = if (listPaciente.doencas_cronicas[0].nome == null){
                            "Não Existe Doenças Crônicas"
                        } else {
                            "${it.nome}"
                        }
                        ProcessingProfileEdit(
                            text = text,
                            width = 200,
                            onValueChange = {
                                var call = RetrofitFactory.getPatient().deleteChronicDiseases(it.id)

                                call.enqueue(object : Callback<DoencaResponse> {
                                    override fun onResponse(
                                        call: Call<DoencaResponse>,
                                        response: Response<DoencaResponse>
                                    ) {
                                        Log.e("deleteDoenca", "onResponse: ${response.body()}")
                                        navController.navigate("edit_profile_screen")
                                    }
                                    override fun onFailure(call: Call<DoencaResponse>, t: Throwable) {
                                        Log.i("deleteDoenca", "onFailure: ${t.message}")
                                    }
                                })
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Cormobidade",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = { isDialogVisibleComorbidity = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "",
                            tint = Color(0xFF35225F),
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
                if (isDialogVisibleComorbidity) {
                    ModalAddComorbidity(
                        isDialogVisibleComorbidity = false,
                        navController = navController,
                        lifecycleScope= lifecycleScope,
                        nav = "edit_profile_screen"
                    )
                }

                LazyRow{
                    items(listPaciente.comorbidades.reversed()) {

                        var text = if (listPaciente.comorbidades[0].nome == null){
                            "Não Existe Comorbidades"
                        } else {
                            "${it.nome}"
                        }
                        ProcessingProfileEdit(
                            text = text,
                            width = 200,
                            onValueChange = {
                                var call = RetrofitFactory.getPatient().deleteComorbidity(it.id)

                                call.enqueue(object : Callback<DoencaResponse> {
                                    override fun onResponse(
                                        call: Call<DoencaResponse>,
                                        response: Response<DoencaResponse>
                                    ) {
                                        Log.e("deleteDoenca", "onResponse: ${response.body()}")
                                        navController.navigate("edit_profile_screen")
                                    }
                                    override fun onFailure(call: Call<DoencaResponse>, t: Throwable) {
                                        Log.i("deleteDoenca", "onFailure: ${t.message}")
                                    }
                                })
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    DefaultButton(
                        onClick = {

                            Log.e("TAG", "EditProfileScreen: $token", )
                            Log.e("TAG", "EditProfileScreen: $id", )
                            Log.e("TAG", "EditProfileScreen: $nomeState", )
                            Log.e("TAG", "EditProfileScreen: $selectedDate", )
                            Log.e("TAG", "EditProfileScreen: $email", )
                            Log.e("TAG", "EditProfileScreen: $senha", )
                            Log.e("TAG", "EditProfileScreen: $cpfState2", )
                            Log.i("joaoooo", "EditProfileScreen:  $imagemState")

                            if (id != null) {
                                isUploading.value = true
                                bitmap.value?.let { bitmap ->
                                    br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen.UploadingImageToFireBase(
                                        bitmap,
                                        context as ComponentActivity
                                    ) { imageURL ->
                                        if (imageURL != null) {
                                            // Aqui, imageURL contém a URL da imagem após o upload bem-sucedido
//                                        Toast.makeText(
//                                            context,
//                                            "Upload Bem-Sucedido. URL: $imageURL",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
                                            Toast.makeText(
                                                context,
                                                "Upload Bem-Sucedido.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            // Continue com a função finalizarCadastro, passando a imageURL se necessário
                                            atualizarDados(
                                                token = token.toString(),
                                                id = id.toInt(),
                                                nome = nomeState.toString(),
                                                data_nascimento = selectedDate.toAmericanDateFormat1(),
                                                email = email.toString(),
                                                senha = senha.toString(),
                                                foto = imageURL, // Use a imageURL
                                                cpf = cpfState2,
                                                id_endereco_paciente = 1,
                                                genero = selectedDrop
                                            )
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Falha ao fazer o upload",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }






                        },
                        text = "Salvar"
                    )
                }


//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(end = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "Histórico Médico",
//                        fontSize = 16.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
//                        fontWeight = FontWeight(500),
//                        color = Color(0xFF35225F)
//                    )
//                    IconButton(
//                        onClick = {}
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.AddCircle,
//                            contentDescription = "",
//                            tint = Color(0xFF35225F),
//                            modifier = Modifier
//                                .size(40.dp)
//                        )
//                    }
//                }
//                MedicalHistory()
//                Spacer(modifier = Modifier.width(14.dp))
//                MedicalHistory()
//                Spacer(modifier = Modifier.width(14.dp))
//                MedicalHistory()
//                Spacer(modifier = Modifier.width(14.dp))
//                MedicalHistory()

            }

        }
    }
}

fun String.toAmericanDateFormat1(
    pattern: String = "yyyy-MM-dd"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}



//Função de Upload
fun UploadingImageToFireBase(
    bitmap: Bitmap,
    context: ComponentActivity,
    callback: (String?) -> Unit
) {

    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/${bitmap}")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

    val imageData = baos.toByteArray()

    imageRef.putBytes(imageData)
        .addOnSuccessListener { taskSnapshot ->
            // Upload bem-sucedido
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                // Aqui você obtém a URL da imagem após o upload
                val imageURL = uri.toString()
                callback(imageURL)
            }.addOnFailureListener { e ->
                // Tratar erro ao obter a URL
                callback(null)
                Log.e("FirebaseStorage", "Erro ao obter a URL da imagem: $e")
            }
        }
        .addOnFailureListener { e ->
            // Tratar erro ao fazer o upload
            callback(null)
            // Você pode imprimir uma mensagem de erro ou registrar o erro para depuração
            Log.e("FirebaseStorage", "Erro ao fazer upload da imagem: $e")
        }
}