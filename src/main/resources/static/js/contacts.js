const viewContactModal=document.getElementById('view_contact_modal')
const url="http://localhost:9000/";
// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
    id: 'view_contact_modal',
    override: true
};

const contactModal=new Modal(viewContactModal,options,instanceOptions);

function openContactModal(){
    contactModal.show();
}
function closeContactModal(){
    contactModal.hide();
}


// async function loadContactData(id){
// try{
//     openContactModal()
//     console.log(id)
//     const data=await (await fetch(`http://localhost:9000/api/contacts/${id}`)).json();
//     console.log(data)
//     document.querySelector('#contact_name').innerHTML=data.name
// }
// catch (err)
// {
//     console.log("Error",err);
// }
// }

async function loadContactData(id) {
    //function call to load data
    console.log(id);
    try {
        const data=await (await fetch(`${url}api/contacts/${id}`)).json();
        console.log(data);
        document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        document.querySelector("#contact_image").src = data.picture;
        document.querySelector("#contact_address").innerHTML = data.address;
        document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
        document.querySelector("#contact_about").innerHTML = data.description;
        const contactFavorite = document.querySelector("#contact_favorite");
        if (data.favorite) {
            contactFavorite.innerHTML =
                "<i class='fas fa-heart text-yellow-400'></i>";
        } else {
            contactFavorite.innerHTML = "Not Favorite Contact";
        }

        document.querySelector("#contact_website").href = data.websiteLink;
        document.querySelector("#contact_website").innerHTML = data.websiteLink;
        document.querySelector("#contact_linkedIn").href = data.linkedInLink;
        document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink;
        openContactModal();
    } catch (error) {
        console.log("Error: ", error);
    }
}

// delete contact
async function deleteContact(id) {
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel",
        customClass: {
            confirmButton: 'bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700',
            cancelButton: 'bg-gray-300 text-black px-4 py-2 rounded hover:bg-gray-400'
        },
        buttonsStyling: false // Important: disables default SweetAlert styles
    }).then((result) => {
        if (result.isConfirmed) {
            const url1 = `${url}user/contacts/delete/` + id;
            window.location.href = url1;
        }
    });
}

