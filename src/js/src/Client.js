import fetch from 'unfetch';

const checkStatus = response =>{
    if(response.ok){
        return response;
    }
    else{
        let error = new Error(response.statusText);
        error.response = response;
        response.json().then(e => {
            error.error = e;
        });
        return Promise.reject(error);
    }
}

export const getAllCustomers = () => fetch('/api/customervehicles')
                                      .then(checkStatus);
export const changeStatus = customer => fetch(
    '/api/vehicles/' + customer.vin, {
        headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify(customer) 
    }).then(checkStatus)
    .catch(error =>{
        console.log(error);
    });

//export default getAllCustomers;