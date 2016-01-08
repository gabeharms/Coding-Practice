
// Comment Box class
var CommentBox = React.createClass({
  render: function() { // Only function in the 'CommentBox' class
    return (
      <div className="commentBox">
        <h1>Comments</h1>
        <CommentList />
        <CommentForm />
      </div>
    );
  }
});

// Comment list Class
var CommentList = React.createClass({
  render: function() {
    return (
      <div className="commentList">
        Hello, world! I am a CommentList.
      </div>
    );
  }
});

// Comment Form Class
var CommentForm = React.createClass({
  render: function() {
    return (
      <div className="commentForm">
        Hello, world! I am a CommentForm.
      </div>
    );
  }
});


// Instantiates the root component, starts the framework, 
// and injects the markup into a raw DOM element, provided as the second argument.
//
// It is important that ReactDOM.render remain at the bottom of the script for
// this tutorial. ReactDOM.render should only be called after the composite components
// have been defined.
ReactDOM.render(
  <CommentBox />,
  document.getElementById('content')
);