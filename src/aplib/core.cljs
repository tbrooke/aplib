(ns aplib.core
(:require  ["@accordproject/cicero-core"   :as cc]
           ["@accordproject/cicero-engine" :as ce]
           ["@accordproject/concerto-core" :as co]))


(def clause (.cc/new Clause))

(println "Hello world!")


(comment

;;;;;;;;;;;  Cicero ;;;;;;;;;;;;;  

;;;; https://docs.accordproject.org/docs/ref-cicero-api.html 
  

  const template = await Template.fromDirectory('./test/data/latedeliveryandpenalty') ;

// load the DSL text for the template

    const testLatePenaltyInput = fs.readFileSync (path.resolve(__dirname, 'text/', 'sample.md'), 'utf8') ;

    const clause = new Clause (template) ;
    clause.parse (testLatePenaltyInput) ;

// get the JSON object created from the parse

    const data = clause.getData ();
  )

(comment

;;;;;;;;;;;;;;;;;   Concerto   ;;;;;;;;;;;;;;;  

;;;;;https://docs.accordproject.org/docs/model-api.html
  
;;; Validating JSON data using a Model
  
const ModelManager = require('@accordproject/concerto-core').ModelManager;
const Concerto = require('@accordproject/concerto-core').Concerto;
const modelManager = new ModelManager();
modelManager.addModelFile( `namespace org.acme.address
concept PostalAddress {
  o String streetAddress optional
  o String postalCode optional
  o String postOfficeBoxNumber optional
  o String addressRegion optional
  o String addressLocality optional
  o String addressCountry optional
}`, 'model.cto');

const postalAddress = {
    $class : 'org.acme.address.PostalAddress',
    streetAddress : '1 Maine Street'
};
const concerto = new Concerto(modelManager);
concerto.validate(postalAddress);


;;;; Validating an Instance ;;;;;;

const postalAddress = {
    $class : 'org.acme.address.PostalAddress',
    missing : '1 Maine Street'
};


;;;  Introspection   ;;;

const typeDeclaration = concerto.getTypeDeclaration (postalAddress);
const fqn = typeDeclaration.getFullyQualifiedName ();
console.log (fqn); // should equal 'org.acme.address.PostalAddress'

  )